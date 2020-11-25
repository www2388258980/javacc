package com.dto.z01;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
				"licenseNo",
				"businessScope",
				"practiceArea",
				"licenseType",
				"fileType",
				"photo",
				"registDate",
				"issueDate",
				"expiryDate"
})
@XmlRootElement(name = "LicenseInfo")
public class Z01LicenseInfo {
		@XmlElement(name = "LicenseNo", required = true, nillable = true)
		protected String licenseNo;
		@XmlElement(name = "BusinessScope", required = true, nillable = true)
		protected String businessScope;
		@XmlElement(name = "PracticeArea", required = true, nillable = true)
		protected String practiceArea;
		@XmlElement(name = "LicenseType", required = true, nillable = true)
		protected String licenseType;
		@XmlElement(name = "FileType", required = true, nillable = true)
		protected String fileType;
		@XmlElement(name = "Photo", required = true, nillable = true)
		protected String photo;
		@XmlElement(name = "RegistDate", required = true, nillable = true)
		protected String registDate;
		@XmlElement(name = "IssueDate", required = true, nillable = true)
		protected String issueDate;
		@XmlElement(name = "ExpiryDate", required = true, nillable = true)
		protected String expiryDate;

		public String getLicenseNo() {
				return licenseNo;
		}

		public void setLicenseNo(String licenseNo) {
				this.licenseNo = licenseNo;
		}

		public String getBusinessScope() {
				return businessScope;
		}

		public void setBusinessScope(String businessScope) {
				this.businessScope = businessScope;
		}

		public String getPracticeArea() {
				return practiceArea;
		}

		public void setPracticeArea(String practiceArea) {
				this.practiceArea = practiceArea;
		}

		public String getLicenseType() {
				return licenseType;
		}

		public void setLicenseType(String licenseType) {
				this.licenseType = licenseType;
		}

		public String getFileType() {
				return fileType;
		}

		public void setFileType(String fileType) {
				this.fileType = fileType;
		}

		public String getPhoto() {
				return photo;
		}

		public void setPhoto(String photo) {
				this.photo = photo;
		}

		public String getRegistDate() {
				return registDate;
		}

		public void setRegistDate(String registDate) {
				this.registDate = registDate;
		}

		public String getIssueDate() {
				return issueDate;
		}

		public void setIssueDate(String issueDate) {
				this.issueDate = issueDate;
		}

		public String getExpiryDate() {
				return expiryDate;
		}

		public void setExpiryDate(String expiryDate) {
				this.expiryDate = expiryDate;
		}
}
