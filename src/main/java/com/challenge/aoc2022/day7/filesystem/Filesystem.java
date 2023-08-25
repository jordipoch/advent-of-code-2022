package com.challenge.aoc2022.day7.filesystem;

import com.challenge.aoc2022.day7.filesystem.exception.ElementAlreadyExistsException;
import com.challenge.aoc2022.day7.filesystem.exception.FolderNotFoundException;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

public class Filesystem {
    private final Directory rootFolder;
    private Directory currentFolder;
    public Filesystem() {
        rootFolder = Directory.createRoot();
        currentFolder = rootFolder;
    }

    public Directory getRootFolder() {
        return rootFolder;
    }

    public Directory getCurrentFolder() {
        return currentFolder;
    }

    public void createFile(String filename, int size) throws ElementAlreadyExistsException {
        currentFolder.addFile(filename, size);
    }

    public void createFolder(String folderName) throws ElementAlreadyExistsException {
        currentFolder.addFolder(folderName);
    }

    public void moveToSubFolder(String folderName) throws FolderNotFoundException {
        currentFolder = currentFolder.getSubFolder(folderName)
                .orElseThrow(() -> new FolderNotFoundException(String.format("Folder \"%s\" doesn't exist", folderName)));
    }

    public void moveToParentFolder() {
        currentFolder = currentFolder.getParent();
    }

    public void moveToRootFolder() {
        currentFolder = rootFolder;
    }

    public Set<Element> findElements(Predicate<Element> condition) {
        Set<Element> result = new HashSet<>();

        if (condition.test(rootFolder))
            result.add(rootFolder);

        result.addAll(rootFolder.findElements(condition));

        return result;
    }

    public int getSpaceUsed() {
        return rootFolder.getSize();
    }

    @Override
    public String toString() {
        return rootFolder.toString();
    }
}
