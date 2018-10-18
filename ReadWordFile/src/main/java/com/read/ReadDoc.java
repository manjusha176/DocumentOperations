package com.read;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.util.StringUtil;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

public class ReadDoc {

	public void readDocFile()
	{
		try {
			//File file = new File("./src/main/resources/Cloud Foundry.docx");
			File file = new File("./src/main/resources");
			
			File[] files = file.listFiles((dir, name) -> name.toLowerCase().endsWith(".docx"));
			
			System.out.println("number of files available : "+ files.length);
			
			for(File f : files)
			{
				System.out.println(f.getName());
				
				FileInputStream fis = new FileInputStream(f.getAbsolutePath());

				XWPFDocument document = new XWPFDocument(fis);

				List<XWPFParagraph> paragraphs = document.getParagraphs();
				
				System.out.println("Total no of Lines "+paragraphs.size());
				int LineNumber = 1;
				
				for (XWPFParagraph para : paragraphs) {
					
					System.out.println(LineNumber + " " + para.getText());
					LineNumber++;
					
					String[] WordPresentInOneLine = para.getText().split(" ");
					
					String WordToSearch = "cloud";
					int count = 0;
					
					for(int j= 0; j< WordPresentInOneLine.length ; j++)
					{
						if(WordToSearch.equalsIgnoreCase(WordPresentInOneLine[j]))
						{
							count++;
						}
					}
					
//					System.out.println("number of words present in line: "+ WordPresentInOneLine.length);
//					String WordToSearch = "cloud";
//					int count=StringUtils.countMatches(para.getText(), WordToSearch);;
					System.out.println("word occurence: "+ count);
				}
				
				fis.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		ReadDoc obj = new ReadDoc();
		obj.readDocFile();
				
	}

}
