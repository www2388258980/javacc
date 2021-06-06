package com.dto.z01;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
				"staffName",
				"credentialType",
				"credentialNo",
				"age",
				"gender",
				"birthDay",
				"educationalLevel",
				"nation",
				"politicalStatus",
				"phoneNo",
				"familyAddress"
})
@XmlRootElement(name = "BaseInfo")
public class Z01BaseInfo {
		@XmlElement(name = "StaffName", required = true, nillable = true)
		protected String staffName;
		@XmlElement(name = "CredentialType", required = true, nillable = true)
		protected String credentialType;
		@XmlElement(name = "CredentialNo", required = true, nillable = true)
		protected String credentialNo;
		@XmlElement(name = "Age", required = true, nillable = true)
		protected String age;
		@XmlElement(name = "Gender", required = true, nillable = true)
		protected String gender;
		@XmlElement(name = "BirthDay", required = true, nillable = true)
		protected String birthDay;
		@XmlElement(name = "EducationalLevel", required = true, nillable = true)
		protected String educationalLevel;
		@XmlElement(name = "Nation", required = true, nillable = true)
		protected String nation;
		@XmlElement(name = "PoliticalStatus", required = true, nillable = true)
		protected String politicalStatus;
		@XmlElement(name = "PhoneNo", required = true, nillable = true)
		protected String phoneNo;
		@XmlElement(name = "FamilyAddress", required = true, nillable = true)
		protected String familyAddress;

		public String getStaffName() {
				return staffName;
		}

		public void setStaffName(String staffName) {
				this.staffName = staffName;
		}

		public String getCredentialType() {
				return credentialType;
		}

		public void setCredentialType(String credentialType) {
				this.credentialType = credentialType;
		}

		public String getCredentialNo() {
				return credentialNo;
		}

		public void setCredentialNo(String credentialNo) {
				this.credentialNo = credentialNo;
		}

		public String getAge() {
				return age;
		}

		public void setAge(String age) {
				this.age = age;
		}

		public String getGender() {
				return gender;
		}

		public void setGender(String gender) {
				this.gender = gender;
		}

		public String getBirthDay() {
				return birthDay;
		}

		public void setBirthDay(String birthDay) {
				this.birthDay = birthDay;
		}

		public String getEducationalLevel() {
				return educationalLevel;
		}

		public void setEducationalLevel(String educationalLevel) {
				this.educationalLevel = educationalLevel;
		}

		public String getNation() {
				return nation;
		}

		public void setNation(String nation) {
				this.nation = nation;
		}

		public String getPoliticalStatus() {
				return politicalStatus;
		}

		public void setPoliticalStatus(String politicalStatus) {
				this.politicalStatus = politicalStatus;
		}

		public String getPhoneNo() {
				return phoneNo;
		}

		public void setPhoneNo(String phoneNo) {
				this.phoneNo = phoneNo;
		}

		public String getFamilyAddress() {
				return familyAddress;
		}

		public void setFamilyAddress(String familyAddress) {
				this.familyAddress = familyAddress;
		}
}
