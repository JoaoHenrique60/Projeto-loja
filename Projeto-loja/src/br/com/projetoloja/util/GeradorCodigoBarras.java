package br.com.projetoloja.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BarcodeEAN;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import br.com.projetoloja.produto.Produto;

public class GeradorCodigoBarras {

	
	public void codigoBarras(ArrayList<Produto> lista) {
		

		Document doc = new Document(PageSize.A4,30,30,30,30);

		try {
			
			PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("Doc.pdf"));

			doc.open();
			
			PdfContentByte cb = writer.getDirectContent();

			BarcodeEAN codigoBarras = new BarcodeEAN();

			codigoBarras.setCodeType(codigoBarras.EAN13);
			
			PdfPTable tabela = new PdfPTable(6);
			tabela.setHorizontalAlignment(20);
			tabela.setWidthPercentage(100);
			
			for (Produto mercadoria : lista) {
				
				if(mercadoria.getId() > 0 && mercadoria.getId() <=9){
				codigoBarras.setCode("980000000000" + mercadoria.getId());
				}else if(mercadoria.getId() >= 10 && mercadoria.getId() <=99){
				codigoBarras.setCode("98000000000" + mercadoria.getId());
				}else if(mercadoria.getId() >= 100 && mercadoria.getId() <=999){
				codigoBarras.setCode("9800000000" + mercadoria.getId());	
				}else if(mercadoria.getId() >= 1000 && mercadoria.getId() <=9999){
				codigoBarras.setCode("980000000" + mercadoria.getId());
				}else if(mercadoria.getId() >= 10000 && mercadoria.getId() <=99999){
				codigoBarras.setCode("98000000" + mercadoria.getId());
				}else if(mercadoria.getId() >= 100000 && mercadoria.getId() <=999999){
				codigoBarras.setCode("9800000" + mercadoria.getId());
				}
				
				
				Image imageEAN = codigoBarras.createImageWithBarcode(cb, null, null);
				
				String especificacoes = "Nome: " + mercadoria.getNome() + "\n" +
									    "Valor: " + mercadoria.getValorVenda();
				
				Font f1 = new Font(FontFamily.COURIER,8,Font.BOLD);
				Paragraph p1 = new Paragraph(especificacoes,f1);
				
				tabela.addCell(p1);
				tabela.addCell(new Phrase(new Chunk(imageEAN, 0, 10)));
				
			}
			
			doc.add(tabela);
			

		} catch (DocumentException e1) {

			System.err.println(e1.getMessage());

		} catch (IOException e2) {

			System.err.println(e2.getMessage());

		} catch (Exception e3) {

			System.err.println(e3.getMessage());
		}finally{
			
			doc.close();
		}

	}

}
