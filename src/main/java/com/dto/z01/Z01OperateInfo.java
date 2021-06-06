package com.dto.z01;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
				"operateType"
})
@XmlRootElement(name = "OperateInfo")
public class Z01OperateInfo {
		@XmlElement(name = "OperateType", required = true, nillable = true)
		protected String operateType;

		public String getOperateType() {
				return operateType;
		}

		public void setOperateType(String operateType) {
				this.operateType = operateType;
		}
}
