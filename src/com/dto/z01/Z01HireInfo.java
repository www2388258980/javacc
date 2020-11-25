package com.dto.z01;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
				"comCode",
				"areaCode",
				"affiCname",
				"channel",
				"hireDate",
				"companyID",
				"contractType",
				"workNature",
				"insuranceTeamFlag",
				"ruralSalesmanFlag",
				"companyPhone",
				"associationPhone",
				"employeeType"
})
@XmlRootElement(name = "HireInfo")
public class Z01HireInfo {
		@XmlElement(name = "ComCode", required = true, nillable = true)
		protected String comCode;
		@XmlElement(name = "AreaCode", required = true, nillable = true)
		protected String areaCode;
		@XmlElement(name = "AffiCname", required = true, nillable = true)
		protected String affiCname;
		@XmlElement(name = "Channel", required = true, nillable = true)
		protected String channel;
		@XmlElement(name = "HireDate", required = true, nillable = true)
		protected String hireDate;
		@XmlElement(name = "CompanyID", required = true, nillable = true)
		protected String companyID;
		@XmlElement(name = "ContractType", required = true, nillable = true)
		protected String contractType;
		@XmlElement(name = "WorkNature", required = true, nillable = true)
		protected String workNature;
		@XmlElement(name = "InsuranceTeamFlag", required = true, nillable = true)
		protected String insuranceTeamFlag;
		@XmlElement(name = "RuralSalesmanFlag", required = true, nillable = true)
		protected String ruralSalesmanFlag;
		@XmlElement(name = "CompanyPhone", required = true, nillable = true)
		protected String companyPhone;
		@XmlElement(name = "AssociationPhone", required = true, nillable = true)
		protected String associationPhone;
		@XmlElement(name = "EmployeeType", required = true, nillable = true)
		protected String employeeType;

		public String getComCode() {
				return comCode;
		}

		public void setComCode(String comCode) {
				this.comCode = comCode;
		}

		public String getAreaCode() {
				return areaCode;
		}

		public void setAreaCode(String areaCode) {
				this.areaCode = areaCode;
		}

		public String getAffiCname() {
				return affiCname;
		}

		public void setAffiCname(String affiCname) {
				this.affiCname = affiCname;
		}

		public String getChannel() {
				return channel;
		}

		public void setChannel(String channel) {
				this.channel = channel;
		}

		public String getHireDate() {
				return hireDate;
		}

		public void setHireDate(String hireDate) {
				this.hireDate = hireDate;
		}

		public String getCompanyID() {
				return companyID;
		}

		public void setCompanyID(String companyID) {
				this.companyID = companyID;
		}

		public String getContractType() {
				return contractType;
		}

		public void setContractType(String contractType) {
				this.contractType = contractType;
		}

		public String getWorkNature() {
				return workNature;
		}

		public void setWorkNature(String workNature) {
				this.workNature = workNature;
		}

		public String getInsuranceTeamFlag() {
				return insuranceTeamFlag;
		}

		public void setInsuranceTeamFlag(String insuranceTeamFlag) {
				this.insuranceTeamFlag = insuranceTeamFlag;
		}

		public String getRuralSalesmanFlag() {
				return ruralSalesmanFlag;
		}

		public void setRuralSalesmanFlag(String ruralSalesmanFlag) {
				this.ruralSalesmanFlag = ruralSalesmanFlag;
		}

		public String getCompanyPhone() {
				return companyPhone;
		}

		public void setCompanyPhone(String companyPhone) {
				this.companyPhone = companyPhone;
		}

		public String getAssociationPhone() {
				return associationPhone;
		}

		public void setAssociationPhone(String associationPhone) {
				this.associationPhone = associationPhone;
		}

		public String getEmployeeType() {
				return employeeType;
		}

		public void setEmployeeType(String employeeType) {
				this.employeeType = employeeType;
		}
}
