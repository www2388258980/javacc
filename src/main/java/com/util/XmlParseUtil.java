package com.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Date;


public class XmlParseUtil {
		private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		public XmlParseUtil() {
		}

		public static String dtoToXml(Object obj, String tEncoding) throws Exception {
				if (tEncoding == null || "".equals(tEncoding)) {
						tEncoding = "GBK";
				}

				JAXBContext context1 = JAXBContext.newInstance(obj.getClass());
				Marshaller m = context1.createMarshaller();
				m.setProperty("jaxb.formatted.output", true);
				m.setProperty("jaxb.encoding", tEncoding);
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				m.marshal(obj, out);
				String xml = out.toString(tEncoding);
				xml = xml == null ? null : xml.replaceAll(" xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"", "");
				xml = xml == null ? null : xml.replaceAll(" standalone=\"yes\"", "");
				return xml == null ? null : xml.replaceAll(" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:nil=\"true\"", "");
		}

		public static String dtoToXml(Object obj) throws Exception {
				return dtoToXml(obj, (String) null);
		}

		public static Object xmlToDto(String xml, Class<?> clazz) throws Exception {
				Unmarshaller um = JAXBContext.newInstance(clazz).createUnmarshaller();
				return um.unmarshal(new StreamSource(new StringReader(xml)));
		}

		public static Object xmlToDto(InputStream in, Class<?> clazz) throws Exception {
				Unmarshaller um = JAXBContext.newInstance(clazz).createUnmarshaller();
				return um.unmarshal(in);
		}


		public static String formatStr(Object obj) {
				if (obj == null) {
						return "";
				} else {
						String str;
						if (Date.class.isAssignableFrom(obj.getClass())) {
								str = sdf.format(obj);
						} else {
								str = obj.toString();
						}

						return str;
				}
		}
}