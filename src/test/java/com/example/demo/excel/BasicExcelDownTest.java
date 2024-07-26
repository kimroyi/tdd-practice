package com.example.demo.excel;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BasicExcelDownTest {

    private static final String TEMP_DIR = "D:\\temp\\excel-test";
    private static final String FILE_NAME = "temp";

    @Test
    void testExcelDownload() throws IOException {
        List<Person> persons = createPersonList();

        Path tempFile;
        // 엑셀 파일 생성
        try (Workbook workbook = createWorkbook(persons)) {

            // 임시 파일 생성
            tempFile = createTempFile();

            // 엑셀파일(Workbook) 디스크에 파일로 저장
            saveWorkbookToFile(workbook, tempFile);
        }

        assertThat(Files.exists(tempFile)).isTrue();
        assertThat(Files.size(tempFile) > 0).isTrue();
    }

    private List<Person> createPersonList() {
        return new ArrayList<>(List.of(
                new Person("김파고", 10),
                new Person("이튜브", 20),
                new Person("남기자", 30),
                new Person("뉴스팡", 40)
        ));
    }

    private Workbook createWorkbook(List<Person> persons) {
        /*
            HSSF: 엑셀 2007 하위(97~2003) 버전(.xls)
            XSSF: 엑셀 2007 이상 버전(.xlsx)
        */
        // 엑셀파일(Workbook) 생성
        Workbook workbook = new XSSFWorkbook();
        // 엑셀파일(Workbook)내 시트 생성 (시트명: Sheet1)
        Sheet sheet = workbook.createSheet("Sheet1");

        int rowNo = 0;
        // 시트에 행(row) 추가
        Row headerRow = sheet.createRow(rowNo++);
        // 추가된 행에 열(cell) 추가
        headerRow.createCell(0).setCellValue("이름");
        headerRow.createCell(1).setCellValue("나이");

        for (Person person : persons) {
            Row row = sheet.createRow(rowNo++);
            row.createCell(0).setCellValue(person.name());
            row.createCell(1).setCellValue(person.age());
        }

        return workbook;
    }

    private Path createTempFile() throws IOException {
        // 임시 파일 생성할 디렉터리 설정
        Path tempDir = Paths.get(TEMP_DIR);
        if (!Files.exists(tempDir)) {
            Files.createDirectories(tempDir);
        }

        // 임시 파일 생성 처리 (엑셀파일 다운로드)
        Path tempFile = Files.createTempFile(tempDir, FILE_NAME, ".xlsx");
        // 임시 생성한 파일 삭제 (JVM이 종료될 때 자동으로 삭제 처리)
        tempFile.toFile().deleteOnExit();

        return tempFile;
    }

    private void saveWorkbookToFile(Workbook workbook, Path tempFile) {
        try (OutputStream fileOut = new FileOutputStream(tempFile.toFile())) {
            workbook.write(fileOut);
        } catch (IOException e) {
            throw new RuntimeException("Error saving the Excel file", e);
        }
    }

    record Person(String name, int age) {
        public Person {
            if (age < 0) {
                throw new IllegalArgumentException("나이는 음수 불가.");
            }
        }
    }
}
