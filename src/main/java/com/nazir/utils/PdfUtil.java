package com.nazir.utils;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.AcroFields.Item;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.codec.Base64.OutputStream;

/**
 * @author luogm
 *
 */
public class PdfUtil {
	
	/**
	 * @throws IOException
	 * @throws DocumentException
	 */
	public void fillTemplate(String templateFile)
			throws IOException, DocumentException {
		PdfReader reader = new PdfReader(templateFile); // 模版文件目录
		PdfStamper ps = new PdfStamper(reader, new FileOutputStream(
				"f:/fillTemplate.pdf")); // 生成的输出流
		// ByteArrayOutputStream bos = new ByteArrayOutputStream();
		// PdfStamper ps = new PdfStamper(reader, bos);

		AcroFields s = ps.getAcroFields();

		Map<String, Item> fieldMap = s.getFields(); // pdf表单相关信息展示
		for (Entry<String, Item> entry : fieldMap.entrySet()) {
			String name = entry.getKey(); // name就是pdf模版中各个文本域的名字
			Item item = (Item) entry.getValue();
			System.out.println("[name]:" + name + ", [value]: " + item);
		}

		s.setField("CUSTOMERNAME", "as该多好公司");
		s.setField("TEL", "123456asdzxc");
		s.setField("CONTACT", "我是联系人123");

		ps.setFormFlattening(true); // 这句不能少
		ps.close();
		reader.close();
	}
	
	/**
	 * 多个PDF合并功能
	 * 
	 * @param files
	 *            多个PDF的文件路径
	 * @param os
	 *            生成的输出流
	 */
	public static void mergePdfFiles(String[] files, OutputStream os) {
		try {
			Document document = new Document(
					new PdfReader(files[0]).getPageSize(1));
			PdfCopy copy = new PdfCopy(document, os);
			document.open();
			for (int i = 0; i < files.length; i++) {
				PdfReader reader = new PdfReader(files[i]);
				int n = reader.getNumberOfPages();
				for (int j = 1; j <= n; j++) {
					document.newPage();
					PdfImportedPage page = copy.getImportedPage(reader, j);
					copy.addPage(page);
				}
			}
			document.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 多个PDF合并功能
	 * 
	 * @param osList
	 * @param os
	 */
	public static void mergePdfFiles(List<ByteArrayOutputStream> osList,
			OutputStream os) {
		try {
			Document document = new Document(new PdfReader(osList.get(0)
					.toByteArray()).getPageSize(1));
			PdfCopy copy = new PdfCopy(document, os);
			document.open();
			for (int i = 0; i < osList.size(); i++) {
				PdfReader reader = new PdfReader(osList.get(i).toByteArray());
				int n = reader.getNumberOfPages();
				for (int j = 1; j <= n; j++) {
					document.newPage();
					PdfImportedPage page = copy.getImportedPage(reader, j);
					copy.addPage(page);
				}
			}
			document.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 单个Pdf文件分割成N个文件
	 * 
	 * @param filepath
	 * @param N
	 */
	public static void partitionPdfFile(String filepath, int N) {
		Document document = null;
		PdfCopy copy = null;

		try {
			PdfReader reader = new PdfReader(filepath);
			int n = reader.getNumberOfPages();
			if (n < N) {
				System.out.println("The document does not have " + N
						+ " pages to partition !");
				return;
			}
			int size = n / N;
			String staticpath = filepath.substring(0,
					filepath.lastIndexOf("\\") + 1);
			String savepath = null;
			List<String> savepaths = new ArrayList<String>();
			for (int i = 1; i <= N; i++) {
				if (i < 10) {
					savepath = filepath.substring(
							filepath.lastIndexOf("\\") + 1,
							filepath.length() - 4);
					savepath = staticpath + savepath + "0" + i + ".pdf";
					savepaths.add(savepath);
				} else {
					savepath = filepath.substring(
							filepath.lastIndexOf("\\") + 1,
							filepath.length() - 4);
					savepath = staticpath + savepath + i + ".pdf";
					savepaths.add(savepath);
				}
			}

			for (int i = 0; i < N - 1; i++) {
				document = new Document(reader.getPageSize(1));
				copy = new PdfCopy(document, new FileOutputStream(
						savepaths.get(i)));
				document.open();
				for (int j = size * i + 1; j <= size * (i + 1); j++) {
					document.newPage();
					PdfImportedPage page = copy.getImportedPage(reader, j);
					copy.addPage(page);
				}
				document.close();
			}

			document = new Document(reader.getPageSize(1));
			copy = new PdfCopy(document, new FileOutputStream(
					savepaths.get(N - 1)));
			document.open();
			for (int j = size * (N - 1) + 1; j <= n; j++) {
				document.newPage();
				PdfImportedPage page = copy.getImportedPage(reader, j);
				copy.addPage(page);
			}
			document.close();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 将文件流直接返回给客户端
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("application/pdf");
		ServletOutputStream sos = resp.getOutputStream();
		FileInputStream in = new FileInputStream("f:/fillTemplate.pdf");
		byte data[] = new byte[1024];

		int len = 0;
		while ((len = in.read(data)) != -1) {
			sos.write(data, 0, len);
		}

		sos.flush();
		in.close();
		sos.close();
	}
	
	/**
	 * 填充模版文件后将输出流返回给客户端
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doGet2(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("application/pdf");

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PdfReader reader = null;
		PdfStamper ps = null;
		try {
			reader = new PdfReader(""); // 模版文件目录
			ps = new PdfStamper(reader, baos);
			AcroFields s = ps.getAcroFields();
			s.setField("CUSTOMERNAME", "as该多好公司");
			s.setField("TEL", "123456asdzxc");
			s.setField("CONTACT", "我是联系人123");

			ps.setFormFlattening(true); // 这句不能少
			ps.close();
			reader.close();
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		ServletOutputStream sos = resp.getOutputStream();
		baos.writeTo(sos);
		sos.flush();
		sos.close();
	}
}
