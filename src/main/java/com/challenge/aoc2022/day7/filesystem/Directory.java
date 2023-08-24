package com.challenge.aoc2022.day7.filesystem;

import com.challenge.aoc2022.day7.filesystem.exception.ElementAlreadyExistsException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.System.lineSeparator;
import static java.util.Objects.requireNonNull;

public class Directory extends Element {
    private final List<Element> elements = new ArrayList<>();
    private final int level;
    private final Directory parent;

    public static Directory createRoot() {
        return new Directory("/");
    }

    public static Directory of(String name, Directory parent) {
        return new Directory(name, parent);
    }

    public Directory(String name) {
        super(name, 0);
        this.parent = this;
        this.level = 0;
    }

    public Directory(String name, Directory parent) {
        super(name, 0);
        this.parent = requireNonNull(parent);
        this.level = parent.level + 1;
    }

    @Override
    public boolean isFile() {
        return false;
    }

    @Override
    public boolean isFolder() {
        return true;
    }

    public void addFile(String filename, int size) throws ElementAlreadyExistsException {
        if(existsElementWithName(filename))
            throw new ElementAlreadyExistsException(String.format("A file or folder with name \"%s\" already exists in the current folder", filename));

        elements.add(File.of(filename, size));
        updateFolderSize(size);
    }

    public void addFolder(String folderName) throws ElementAlreadyExistsException {
        if(existsElementWithName(folderName))
            throw new ElementAlreadyExistsException(String.format("A file or folder with name \"%s\" already exists in the current folder", folderName));
        else
            elements.add(of(folderName, this));
    }

    public Set<Element> findElements(Predicate<Element> condition) {
        return elements.stream()
                .flatMap(element -> addRecursively(element, condition))
                .collect(Collectors.toSet());
    }

    private Stream<Element> addRecursively(Element element, Predicate<Element> condition) {
        Set<Element> result = new HashSet<>();

        if (condition.test(element))
            result.add(element);

        if (element.isFolder()) {
            var directory = (Directory) element;
            result.addAll(directory.findElements(condition));
        }

        return result.stream();
    }

    private void updateFolderSize(int size) {
        this.size += size;
        if (!isRootFolder())
            parent.updateFolderSize(size);
    }

    private boolean isRootFolder() {
        return parent == this;
    }

    private boolean existsElementWithName(String name) {
        return elements.stream()
                .anyMatch(e -> e.getName().equals(name));
    }

    public Optional<Directory> getSubFolder(String folderName) {
        return elements.stream()
                .filter(Predicate.not(Element::isFile))
                .filter(e -> e.getName().equals(folderName))
                .map(Directory.class::cast)
                .findFirst();
    }

    public Directory getParent() {
        return parent;
    }

    @Override
    public String toString() {
        var sb = new StringBuilder(String.format("%s- %s (dir, size=%d)", createTabs(level), name, size));
        for (var element : elements) {
            sb.append(lineSeparator());
            if (element.isFile()) {
                sb.append(createTabs(level + 1));
            }
            sb.append(element);
        }
        return sb.toString();
    }

    private String createTabs(int n) {
        return Stream.generate(() -> "\t")
                .limit(n)
                .collect(Collectors.joining());
    }
}
