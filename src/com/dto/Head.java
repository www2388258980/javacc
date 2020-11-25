package com.dto;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
				"requestType",
				"userCode",
				"passWord"
})
@XmlRootElement(name = "Head")
public class Head {

		@XmlElement(name = "RequestType", required = true, nillable = true)
		protected String requestType;
		@XmlElement(name = "UserCode", required = true, nillable = true)
		protected String userCode;
		@XmlElement(name = "PassWord", required = true, nillable = true)
		protected String passWord;

		public String getRequestType() {
				return requestType;
		}

		public void setRequestType(String requestType) {
				this.requestType = requestType;
		}

		public String getUserCode() {
				return userCode;
		}

		public void setUserCode(String userCode) {
				this.userCode = userCode;
		}

		public String getPassWord() {
				return passWord;
		}

		public void setPassWord(String passWord) {
				this.passWord = passWord;
		}
}
