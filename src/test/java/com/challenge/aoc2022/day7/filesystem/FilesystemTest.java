package com.challenge.aoc2022.day7.filesystem;

import com.challenge.aoc2022.day7.filesystem.exception.ElementAlreadyExistsException;
import com.challenge.aoc2022.day7.filesystem.exception.FilesystemException;
import com.challenge.aoc2022.day7.filesystem.exception.FolderNotFoundException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FilesystemTest {
    private Filesystem filesystem;

    @BeforeMethod
    public void setUp() {
        filesystem = new Filesystem();
    }

    @Test
    public void testGetCurrentFolderShouldBeRootUponCreation() {
        assertThat(filesystem.getCurrentFolder()).isEqualTo(filesystem.getRootFolder());
    }

    @Test
    public void testCreateFilesInRootFolder() throws FilesystemException {
        filesystem.createFile("a.txt", 44);
        filesystem.createFile("b.txt", 16);
        filesystem.createFile("c.txt", 10);

        System.out.println(filesystem);
        assertThat(filesystem).hasToString("""
                - / (dir, size=70)
                \t- a.txt (file, size=44)
                \t- b.txt (file, size=16)
                \t- c.txt (file, size=10)"""
        );
    }

    @Test
    public void testCreateDirectoryWithFiles() throws FilesystemException {
        filesystem.createFile("a.txt", 44);
        filesystem.createFile("b.txt", 16);
        filesystem.createFolder("folder 1");
        filesystem.moveToSubFolder("folder 1");
        filesystem.createFile("c.txt", 10);
        filesystem.createFile("d.txt", 20);


        System.out.println(filesystem);
        assertThat(filesystem).hasToString("""
                - / (dir, size=90)
                \t- a.txt (file, size=44)
                \t- b.txt (file, size=16)
                \t- folder 1 (dir, size=30)
                \t\t- c.txt (file, size=10)
                \t\t- d.txt (file, size=20)"""
        );
    }

    @Test
    public void testCreateEmptyDirectory() throws FilesystemException {
        filesystem.createFile("a.txt", 44);
        filesystem.createFolder("folder 1");
        filesystem.createFile("b.txt", 20);


        System.out.println(filesystem);
        assertThat(filesystem).hasToString("""
                - / (dir, size=64)
                \t- a.txt (file, size=44)
                \t- folder 1 (dir, size=0)
                \t- b.txt (file, size=20)"""
        );
    }

    @Test
    public void testWithTwoLevelHierarchy() throws FilesystemException {
        filesystem.createFile("a.txt", 44);
        filesystem.createFolder("folder 1");
        filesystem.moveToSubFolder("folder 1");
        filesystem.createFolder("folder 2");
        filesystem.moveToSubFolder("folder 2");
        filesystem.createFile("b.txt", 20);
        filesystem.createFile("c.txt", 12);
        filesystem.moveToParentFolder();
        filesystem.createFolder("folder 3");
        filesystem.moveToSubFolder("folder 3");
        filesystem.createFile("d.txt", 8);
        filesystem.createFile("e.txt", 16);
        filesystem.moveRootFolder();
        filesystem.createFile("f.txt", 25);


        System.out.println(filesystem);
        assertThat(filesystem).hasToString("""
                - / (dir, size=125)
                \t- a.txt (file, size=44)
                \t- folder 1 (dir, size=56)
                \t\t- folder 2 (dir, size=32)
                \t\t\t- b.txt (file, size=20)
                \t\t\t- c.txt (file, size=12)
                \t\t- folder 3 (dir, size=24)
                \t\t\t- d.txt (file, size=8)
                \t\t\t- e.txt (file, size=16)
                \t- f.txt (file, size=25)"""
        );
    }

    @Test
    public void testTryingToMoveAboveRootShouldStayAtRoot() throws FilesystemException {
        filesystem.createFolder("folder");
        filesystem.moveToSubFolder("folder");
        filesystem.moveToParentFolder();
        filesystem.moveToParentFolder();

        assertThat(filesystem.getCurrentFolder()).isEqualTo(filesystem.getRootFolder());
    }

    @Test(expectedExceptions = FolderNotFoundException.class)
    public void testMoveToNonExistingFolderShouldThrowException() throws FilesystemException {
        filesystem.createFolder("folder");
        filesystem.moveToSubFolder("folder2");
    }

    @Test(expectedExceptions = ElementAlreadyExistsException.class)
    public void testCreateFileWithExistingNameShouldThrowAnException() throws FilesystemException {
        filesystem.createFolder("file");
        filesystem.createFile("file", 40);
    }

    @Test(expectedExceptions = ElementAlreadyExistsException.class)
    public void testCreateFolderWithExistingNameShouldThrowAnException() throws FilesystemException {
        filesystem.createFile("file", 40);
        filesystem.createFolder("file");
    }

    @Test
    public void testFindElementsWithResults() throws FilesystemException {
        filesystem.createFile("a.txt", 44);
        filesystem.createFile("b.txt", 20);
        filesystem.createFolder("folder");
        filesystem.moveToSubFolder("folder");
        filesystem.createFile("a.txt", 15);

        var results = filesystem.findElements(e -> e.getName().equals("a.txt"));
        assertThat(results).extracting(Element::getName).containsExactlyInAnyOrder("a.txt", "a.txt");
    }

    @Test
    public void testFindElementsWithResults2() throws FilesystemException {
        filesystem.createFile("a.txt", 44);
        filesystem.createFolder("folder 1");
        filesystem.moveToSubFolder("folder 1");
        filesystem.createFolder("folder 2");
        filesystem.moveToSubFolder("folder 2");
        filesystem.createFile("b.txt", 20);
        filesystem.createFile("c.txt", 12);
        filesystem.moveToParentFolder();
        filesystem.createFolder("folder 3");
        filesystem.moveToSubFolder("folder 3");
        filesystem.createFile("d.txt", 8);
        filesystem.createFile("e.txt", 16);
        filesystem.moveRootFolder();
        filesystem.createFile("f.txt", 25);

        System.out.println(filesystem);

        var results = filesystem.findElements(e -> e.getSize() <= 100 && e.isFolder());
        assertThat(results).extracting(Element::getName).containsExactlyInAnyOrder("folder 1", "folder 2", "folder 3");
    }

    @Test
    public void testFindElementsWithNoResults() throws FilesystemException {
        filesystem.createFile("a.txt", 44);
        filesystem.createFile("b.txt", 20);
        filesystem.createFolder("folder");
        filesystem.moveToSubFolder("folder");
        filesystem.createFile("a.txt", 15);

        System.out.println(filesystem);

        var results = filesystem.findElements(e -> e.getName().equals("c.txt"));
        assertThat(results).extracting(Element::getName).isEmpty();
    }
}