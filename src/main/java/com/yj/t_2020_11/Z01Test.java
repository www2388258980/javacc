package com.yj.t_2020_11;


import com.dto.z01.*;
import com.util.HttpClientUtil;
import com.util.XmlParseUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Z01Test {
		public static void main(String[] args) throws Exception {
//				File file = new File("D:\\springBoot_self\\javacc\\resources\\txt\\1.txt");
//				BufferedReader br = new BufferedReader(new FileReader(file));
				Z01PACKET packet = new Z01PACKET();
				packet.getHead().setRequestType("Z01");
				packet.getHead().setUserCode("MSLICS");
				packet.getHead().setPassWord("123456");
				Z01OperateInfo operateInfo = new Z01OperateInfo();
				Z01BaseInfo baseInfo = new Z01BaseInfo();
				Z01HireInfo hireInfo = new Z01HireInfo();
				Z01LicenseInfo licenseInfo = new Z01LicenseInfo();

				operateInfo.setOperateType("01");

				baseInfo.setStaffName("曹世*");
				baseInfo.setCredentialType("01");
				baseInfo.setCredentialNo("13052*198*1214662*");
				baseInfo.setAge("31");
				baseInfo.setGender("2");
				baseInfo.setBirthDay("19891214");
				baseInfo.setEducationalLevel("03");
				baseInfo.setNation("01");
				baseInfo.setPoliticalStatus("01");
				baseInfo.setPhoneNo("134*4226612");
				baseInfo.setFamilyAddress("上海市");

				hireInfo.setComCode("MSLI*S320300");
				hireInfo.setAreaCode("320300");
				hireInfo.setAffiCname("徐州市");
				hireInfo.setChannel("01");
				hireInfo.setHireDate("20201124");
				hireInfo.setCompanyID("120562826");
				hireInfo.setContractType("01");
				hireInfo.setWorkNature("02");
				hireInfo.setInsuranceTeamFlag("01");
				hireInfo.setRuralSalesmanFlag("01");
				hireInfo.setCompanyPhone("876*3456");
				hireInfo.setAssociationPhone("67890009");
				hireInfo.setEmployeeType("01");

				licenseInfo.setLicenseNo("5445454fsdfsdf");
				licenseInfo.setBusinessScope("205");
				licenseInfo.setPracticeArea("江苏");
				licenseInfo.setLicenseType("01");
				licenseInfo.setFileType("01");
				licenseInfo.setPhoto("3487598sldjfsljfdls345435435345");
				licenseInfo.setRegistDate("20201010");
				licenseInfo.setIssueDate("20201010");
				licenseInfo.setExpiryDate("20221010");

				packet.getBody().setOperateInfo(operateInfo);
				packet.getBody().setBaseInfo(baseInfo);
				packet.getBody().setHireInfo(hireInfo);
				packet.getBody().setLicenseInfo(licenseInfo);


				String xml = XmlParseUtil.dtoToXml(packet, "GBK");
				System.out.println(xml);
				// 请求接口
				String url = "http://10.2*.4.24:9083/sinoisptService/interfaceServer";
				String resXml = HttpClientUtil.doPost(url, xml, "GBK");

				System.out.println(resXml);
		}
}
