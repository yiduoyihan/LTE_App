package com.leidi.lteapp.util;

import android.os.Environment;
import android.util.Log;
import android.util.Xml;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Table;
import org.apache.poi.hwpf.usermodel.TableCell;
import org.apache.poi.hwpf.usermodel.TableIterator;
import org.apache.poi.hwpf.usermodel.TableRow;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.xmlpull.v1.XmlPullParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class WordUtil {
    private final static String TAG = "WordUtil";
    public String htmlPath;
    private String docPath;
    private String picturePath;
    private List<Picture> pictures;
    private TableIterator tableIterator;
    private int presentPicture = 0;
    private FileOutputStream output;

    private String htmlBegin = "<html><meta charset=\"utf-8\"><body>";
    private String htmlEnd = "</body></html>";
    private String tableBegin = "<table style=\"border-collapse:collapse\" border=1 bordercolor=\"black\">";
    private String tableEnd = "</table>";
    private String rowBegin = "<tr>", rowEnd = "</tr>";
    private String columnBegin = "<td>", columnEnd = "</td>";
    private String lineBegin = "<p>", lineEnd = "</p>";
    private String centerBegin = "<center>", centerEnd = "</center>";
    private String boldBegin = "<b>", boldEnd = "</b>";
    private String underlineBegin = "<u>", underlineEnd = "</u>";
    private String italicBegin = "<i>", italicEnd = "</i>";
    private String fontSizeTag = "<font size=\"%d\">";
    private String fontColorTag = "<font color=\"%s\">";
    private String fontEnd = "</font>";
    private String spanColor = "<span style=\"color:%s;\">", spanEnd = "</span>";
    private String divRight = "<div align=\"right\">", divEnd = "</div>";
    private String imgBegin = "<img src=\"%s\" >";

    public WordUtil(String doc_name) {
        docPath = doc_name;
        htmlPath = FileUtil.createFile("html", FileUtil.getFileName(docPath) + ".html");
        Log.d(TAG, "htmlPath=" + htmlPath);
        try {
            output = new FileOutputStream(htmlPath);
            presentPicture = 0;
            output.write(htmlBegin.getBytes());
            if (docPath.endsWith(".doc")) {
                readDOC();
            } else if (docPath.endsWith(".docx")) {
                readDOCX();
            }
            //????????????????????????
            output.write(htmlEnd.getBytes());
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public WordUtil(){

    }

    //??????word?????????????????????sd?????????html?????????
    private void readDOC() {
        try {
            FileInputStream in = new FileInputStream(docPath);
            POIFSFileSystem pfs = new POIFSFileSystem(in);
            HWPFDocument hwpf = new HWPFDocument(pfs);
            Range range = hwpf.getRange();
            pictures = hwpf.getPicturesTable().getAllPictures();
            tableIterator = new TableIterator(range);
            int numParagraphs = range.numParagraphs();// ??????????????????????????????
            for (int i = 0; i < numParagraphs; i++) { // ???????????????
                Paragraph p = range.getParagraph(i); // ?????????????????????????????????
                if (p.isInTable()) {
                    int temp = i;
                    if (tableIterator.hasNext()) {
                        Table table = tableIterator.next();
                        output.write(tableBegin.getBytes());
                        int rows = table.numRows();
                        for (int r = 0; r < rows; r++) {
                            output.write(rowBegin.getBytes());
                            TableRow row = table.getRow(r);
                            int cols = row.numCells();
                            int rowNumParagraphs = row.numParagraphs();
                            int colsNumParagraphs = 0;
                            for (int c = 0; c < cols; c++) {
                                output.write(columnBegin.getBytes());
                                TableCell cell = row.getCell(c);
                                int max = temp + cell.numParagraphs();
                                colsNumParagraphs = colsNumParagraphs + cell.numParagraphs();
                                for (int cp = temp; cp < max; cp++) {
                                    Paragraph p1 = range.getParagraph(cp);
                                    output.write(lineBegin.getBytes());
                                    writeParagraphContent(p1);
                                    output.write(lineEnd.getBytes());
                                    temp++;
                                }
                                output.write(columnEnd.getBytes());
                            }
                            int max1 = temp + rowNumParagraphs;
                            for (int m = temp + colsNumParagraphs; m < max1; m++) {
                                temp++;
                            }
                            output.write(rowEnd.getBytes());
                        }
                        output.write(tableEnd.getBytes());
                    }
                    i = temp;
                } else {
                    output.write(lineBegin.getBytes());
                    writeParagraphContent(p);
                    output.write(lineEnd.getBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readDOCX() {
        try {
            ZipFile docxFile = new ZipFile(new File(docPath));
//            ZipFile docxFile = new ZipFile(getNetUrlHttp(docPath));
            ZipEntry sharedStringXML = docxFile.getEntry("word/document.xml");
            InputStream inputStream = docxFile.getInputStream(sharedStringXML);
            XmlPullParser xmlParser = Xml.newPullParser();
            xmlParser.setInput(inputStream, "utf-8");
            boolean isTable = false; // ??????
            boolean isSize = false; // ????????????
            boolean isColor = false; // ????????????
            boolean isCenter = false; // ????????????
            boolean isRight = false; // ????????????
            boolean isItalic = false; // ??????
            boolean isUnderline = false; // ?????????
            boolean isBold = false; // ??????
            boolean isRegion = false; // ??????????????????
            int pic_ndex = 1; // docx??????????????????image1????????????????????????1??????
            int event_type = xmlParser.getEventType();
            while (event_type != XmlPullParser.END_DOCUMENT) {
                switch (event_type) {
                    case XmlPullParser.START_TAG: // ????????????
                        String tagBegin = xmlParser.getName();
                        if (tagBegin.equalsIgnoreCase("r")) {
                            isRegion = true;
                        }
                        if (tagBegin.equalsIgnoreCase("jc")) { // ??????????????????
                            String align = xmlParser.getAttributeValue(0);
                            if (align.equals("center")) {
                                output.write(centerBegin.getBytes());
                                isCenter = true;
                            }
                            if (align.equals("right")) {
                                output.write(divRight.getBytes());
                                isRight = true;
                            }
                        }
                        if (tagBegin.equalsIgnoreCase("color")) { // ??????????????????
                            String color = xmlParser.getAttributeValue(0);
                            output.write(String.format(spanColor, color).getBytes());
                            isColor = true;
                        }
                        if (tagBegin.equalsIgnoreCase("sz")) { // ??????????????????
                            if (isRegion == true) {
                                int size = getSize(Integer.valueOf(xmlParser.getAttributeValue(0)));
                                output.write(String.format(fontSizeTag, size).getBytes());
                                isSize = true;
                            }
                        }
                        if (tagBegin.equalsIgnoreCase("tbl")) { // ???????????????
                            output.write(tableBegin.getBytes());
                            isTable = true;
                        } else if (tagBegin.equalsIgnoreCase("tr")) { // ?????????
                            output.write(rowBegin.getBytes());
                        } else if (tagBegin.equalsIgnoreCase("tc")) { // ?????????
                            output.write(columnBegin.getBytes());
                        }
                        if (tagBegin.equalsIgnoreCase("pic")) { // ???????????????
                            ZipEntry pic_entry = FileUtil.getPicEntry(docxFile, pic_ndex);
                            if (pic_entry != null) {
                                byte[] pictureBytes = FileUtil.getPictureBytes(docxFile, pic_entry);
                                writeDocumentPicture(pictureBytes);
                            }
                            pic_ndex++; // ????????????????????????+1
                        }
                        if (tagBegin.equalsIgnoreCase("p") && !isTable) {// ?????????????????????????????????????????????
                            output.write(lineBegin.getBytes());
                        }
                        if (tagBegin.equalsIgnoreCase("b")) { // ???????????????
                            isBold = true;
                        }
                        if (tagBegin.equalsIgnoreCase("u")) { // ??????????????????
                            isUnderline = true;
                        }
                        if (tagBegin.equalsIgnoreCase("i")) { // ???????????????
                            isItalic = true;
                        }
                        // ???????????????
                        if (tagBegin.equalsIgnoreCase("t")) {
                            if (isBold == true) { // ??????
                                output.write(boldBegin.getBytes());
                            }
                            if (isUnderline == true) { // ???????????????????????????<u>
                                output.write(underlineBegin.getBytes());
                            }
                            if (isItalic == true) { // ????????????????????????<i>
                                output.write(italicBegin.getBytes());
                            }
                            String text = xmlParser.nextText();
                            output.write(text.getBytes()); // ????????????
                            if (isItalic == true) { // ????????????????????????</i>
                                output.write(italicEnd.getBytes());
                                isItalic = false;
                            }
                            if (isUnderline == true) { // ???????????????????????????</u>
                                output.write(underlineEnd.getBytes());
                                isUnderline = false;
                            }
                            if (isBold == true) { // ????????????????????????</b>
                                output.write(boldEnd.getBytes());
                                isBold = false;
                            }
                            if (isSize == true) { // ????????????????????????</font>
                                output.write(fontEnd.getBytes());
                                isSize = false;
                            }
                            if (isColor == true) { // ????????????????????????</span>
                                output.write(spanEnd.getBytes());
                                isColor = false;
                            }
//                      if (isCenter == true) { // ????????????????????????</center>??????????????????????????????????????????????????????????????????????????????
//                          output.write(centerEnd.getBytes());
//                          isCenter = false;
//                      }
                            if (isRight == true) { // ????????????????????????</div>
                                output.write(divEnd.getBytes());
                                isRight = false;
                            }
                        }
                        break;
                    // ????????????
                    case XmlPullParser.END_TAG:
                        String tagEnd = xmlParser.getName();
                        if (tagEnd.equalsIgnoreCase("tbl")) { // ????????????????????????</table>
                            output.write(tableEnd.getBytes());
                            isTable = false;
                        }
                        if (tagEnd.equalsIgnoreCase("tr")) { // ???????????????????????????</tr>
                            output.write(rowEnd.getBytes());
                        }
                        if (tagEnd.equalsIgnoreCase("tc")) { // ???????????????????????????</td>
                            output.write(columnEnd.getBytes());
                        }
                        if (tagEnd.equalsIgnoreCase("p")) { // ????????????????????????</p>??????????????????????????????
                            if (isTable == false) {
                                if (isCenter == true) { // ????????????????????????</center>
                                    output.write(centerEnd.getBytes());
                                    isCenter = false;
                                }
                                output.write(lineEnd.getBytes());
                            }
                        }
                        if (tagEnd.equalsIgnoreCase("r")) {
                            isRegion = false;
                        }
                        break;
                    default:
                        break;
                }
                event_type = xmlParser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int getSize(int sizeType) {
        if (sizeType >= 1 && sizeType <= 8) {
            return 1;
        } else if (sizeType >= 9 && sizeType <= 11) {
            return 2;
        } else if (sizeType >= 12 && sizeType <= 14) {
            return 3;
        } else if (sizeType >= 15 && sizeType <= 19) {
            return 4;
        } else if (sizeType >= 20 && sizeType <= 29) {
            return 5;
        } else if (sizeType >= 30 && sizeType <= 39) {
            return 6;
        } else if (sizeType >= 40) {
            return 7;
        } else {
            return 3;
        }
    }

    private String getColor(int colorType) {
        if (colorType == 1) {
            return "#000000";
        } else if (colorType == 2) {
            return "#0000FF";
        } else if (colorType == 3 || colorType == 4) {
            return "#00FF00";
        } else if (colorType == 5 || colorType == 6) {
            return "#FF0000";
        } else if (colorType == 7) {
            return "#FFFF00";
        } else if (colorType == 8) {
            return "#FFFFFF";
        } else if (colorType == 9 || colorType == 15) {
            return "#CCCCCC";
        } else if (colorType == 10 || colorType == 11) {
            return "#00FF00";
        } else if (colorType == 12 || colorType == 16) {
            return "#080808";
        } else if (colorType == 13 || colorType == 14) {
            return "#FFFF00";
        } else {
            return "#000000";
        }
    }

    public void writeDocumentPicture(byte[] pictureBytes) {
        picturePath = FileUtil.createFile("html", FileUtil.getFileName(docPath) + presentPicture + ".jpg");
        FileUtil.writePicture(picturePath, pictureBytes);
        presentPicture++;
        String imageString = String.format(imgBegin, picturePath);
        try {
            output.write(imageString.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeParagraphContent(Paragraph paragraph) {
        Paragraph p = paragraph;
        int pnumCharacterRuns = p.numCharacterRuns();
        for (int j = 0; j < pnumCharacterRuns; j++) {
            CharacterRun run = p.getCharacterRun(j);
            if (run.getPicOffset() == 0 || run.getPicOffset() >= 1000) {
                if (presentPicture < pictures.size()) {
                    writeDocumentPicture(pictures.get(presentPicture).getContent());
                }
            } else {
                try {
                    String text = run.text();
                    if (text.length() >= 2 && pnumCharacterRuns < 2) {
                        output.write(text.getBytes());
                    } else {
                        String fontSizeBegin = String.format(fontSizeTag, getSize(run.getFontSize()));
                        String fontColorBegin = String.format(fontColorTag, getColor(run.getColor()));
                        output.write(fontSizeBegin.getBytes());
                        output.write(fontColorBegin.getBytes());
                        if (run.isBold()) {
                            output.write(boldBegin.getBytes());
                        }
                        if (run.isItalic()) {
                            output.write(italicBegin.getBytes());
                        }
                        output.write(text.getBytes());
                        if (run.isBold()) {
                            output.write(boldEnd.getBytes());
                        }
                        if (run.isItalic()) {
                            output.write(italicEnd.getBytes());
                        }
                        output.write(fontEnd.getBytes());
                        output.write(fontEnd.getBytes());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public File getNetUrlHttp(String path){
        //????????????????????????path???http?????????????????????????????????????????????
        String newUrl = path;
        newUrl = newUrl.split("[?]")[0];
        String[] bb = newUrl.split("/");
        //???????????????????????????????????????
        String fileName = bb[bb.length - 1];
        //????????????????????????Android/data/com.leidi.lteapp/html
        String filePath=Environment.getExternalStorageDirectory()+"/Android/data/com.leidi.lteapp/html/"+fileName;
        Log.d("TAG", "path=" + filePath);
        File file = null;

        URL urlfile;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try{
            //????????????????????????????????????????????????????????????
            file = new File(filePath);
            if(!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }
            try{
                //????????????
                file.createNewFile();
            }catch (Exception e){
                e.printStackTrace();
            }
            //??????
            urlfile = new URL(newUrl);
            inputStream = urlfile.openStream();
            outputStream = new FileOutputStream(file);

            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead=inputStream.read(buffer,0,8192))!=-1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (null != outputStream) {
                    outputStream.close();
                }
                if (null != inputStream) {
                    inputStream.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return file;
    }
}