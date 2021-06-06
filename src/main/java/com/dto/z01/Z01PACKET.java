package com.dto.z01;


import com.dto.Head;

import javax.xml.bind.annotation.*;

/**
 * 入司登记生成上报xml报文
 *
 * @author 杨吉
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
				"head",
				"body"
})
@XmlRootElement(name = "Packet")
public class Z01PACKET {
		@XmlElement(name = "Head", required = true)
		protected Head head;
		@XmlElement(name = "Body", required = true)
		protected Z01Body body;
		@XmlAttribute
		protected String type = "REQUEST";
		@XmlAttribute
		protected String version = "1.0";

		public Z01PACKET() {
				this.head = new Head();
				this.body = new Z01Body();
		}

		public Head getHead() {
				return head;
		}

		public void setHead(Head head) {
				this.head = head;
		}

		public Z01Body getBody() {
				return body;
		}

		public void setBody(Z01Body body) {
				this.body = body;
		}

		public String getType() {
				return type;
		}

		public void setType(String type) {
				this.type = type;
		}

		public String getVersion() {
				return version;
		}

		public void setVersion(String version) {
				this.version = version;
		}
}
