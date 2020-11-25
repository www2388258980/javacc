package com.dto.z01;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
				"baseInfo",
				"hireInfo",
				"licenseInfo",
				"operateInfo"
})
@XmlRootElement(name = "Body")
public class Z01Body {
		@XmlElement(name = "BaseInfo", required = true)
		protected Z01BaseInfo baseInfo;

		@XmlElement(name = "HireInfo", required = true)
		protected Z01HireInfo hireInfo;

		@XmlElement(name = "LicenseInfo", required = true)
		protected Z01LicenseInfo licenseInfo;

		@XmlElement(name = "OperateInfo", required = true)
		protected Z01OperateInfo operateInfo;

		public Z01BaseInfo getBaseInfo() {
				return baseInfo;
		}

		public void setBaseInfo(Z01BaseInfo baseInfo) {
				this.baseInfo = baseInfo;
		}

		public Z01HireInfo getHireInfo() {
				return hireInfo;
		}

		public void setHireInfo(Z01HireInfo hireInfo) {
				this.hireInfo = hireInfo;
		}

		public Z01LicenseInfo getLicenseInfo() {
				return licenseInfo;
		}

		public void setLicenseInfo(Z01LicenseInfo licenseInfo) {
				this.licenseInfo = licenseInfo;
		}

		public Z01OperateInfo getOperateInfo() {
				return operateInfo;
		}

		public void setOperateInfo(Z01OperateInfo operateInfo) {
				this.operateInfo = operateInfo;
		}
}
