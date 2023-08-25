package com.challenge.aoc2022.day7.filesystem;

import com.challenge.aoc2022.day7.filesystem.exception.ElementAlreadyExistsException;
import com.challenge.aoc2022.day7.filesystem.exception.FilesystemException;
import com.challenge.aoc2022.day7.filesystem.exception.FolderNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FilesystemTest {
    private static final Logger logger = LogManager.getLogger();
    private Filesystem filesystem;

    @BeforeMethod
    public void setUp() {
        filesystem = new Filesystem();
    }

    @Test
    public void testFileSystemCreation() throws FilesystemException {
        createAndFillFilesystem();
        logger.debug("Filesystem contents: {}{}", System.lineSeparator(), filesystem);
        assertThat(filesystem.getSpaceUsed()).isEqualTo(48_381_165);
        assertThat(filesystem).hasToString(getExpectedStringRepresentation());
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
        createAndFillFilesystem();
        logger.debug("Filesystem contents: {}{}", System.lineSeparator(), filesystem);

        var results = filesystem.findElements(e -> e.getSize() <= 100_000 && e.isFolder());
        assertThat(results).extracting(Element::getName).containsExactlyInAnyOrder("a", "e");
    }

    @Test
    public void testFindElementsWithNoResults() throws FilesystemException {
        createAndFillFilesystem();
        logger.debug("Filesystem contents: {}{}", System.lineSeparator(), filesystem);

        var results = filesystem.findElements(e -> e.getName().equals("c.txt"));
        assertThat(results).extracting(Element::getName).isEmpty();
    }

    private void createAndFillFilesystem() throws FilesystemException {
        filesystem = new Filesystem();

        filesystem.moveToRootFolder();
        filesystem.createFolder("a");
        filesystem.createFile("b.txt", 14_848_514);
        filesystem.createFile("c.dat", 8_504_156);
        filesystem.createFolder("d");
        filesystem.moveToSubFolder("a");
        filesystem.createFolder("e");
        filesystem.createFile("f", 29_116);
        filesystem.createFile("g", 2_557);
        filesystem.createFile("h.lst", 62_596);
        filesystem.moveToSubFolder("e");
        filesystem.createFile("i", 584);
        filesystem.moveToParentFolder();
        filesystem.moveToParentFolder();
        filesystem.moveToSubFolder("d");
        filesystem.createFile("j", 4_060_174);
        filesystem.createFile("d.log", 8_033_020);
        filesystem.createFile("d.ext", 5_626_152);
        filesystem.createFile("k", 7_214_296);
    }

    private String getExpectedStringRepresentation() {
        return """
                - / (dir, size=48381165)
                \t- a (dir, size=94853)
                \t\t- e (dir, size=584)
                \t\t\t- i (file, size=584)
                \t\t- f (file, size=29116)
                \t\t- g (file, size=2557)
                \t\t- h.lst (file, size=62596)
                \t- b.txt (file, size=14848514)
                \t- c.dat (file, size=8504156)
                \t- d (dir, size=24933642)
                \t\t- j (file, size=4060174)
                \t\t- d.log (file, size=8033020)
                \t\t- d.ext (file, size=5626152)
                \t\t- k (file, size=7214296)""";
    }
}