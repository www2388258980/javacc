package org.apache.toolcabinet.map.baidu;

public class Result {
		private String name;
		private Location location;
		private String address;
		private String province;
		private String city;
		private String area;
		private String detail;
		private String uid;

		public String getName() {
				return name;
		}

		public void setName(String name) {
				this.name = name;
		}

		public Location getLocation() {
				return location;
		}

		public void setLocation(Location location) {
				this.location = location;
		}

		public String getAddress() {
				return address;
		}

		public void setAddress(String address) {
				this.address = address;
		}

		public String getProvince() {
				return province;
		}

		public void setProvince(String province) {
				this.province = province;
		}

		public String getCity() {
				return city;
		}

		public void setCity(String city) {
				this.city = city;
		}

		public String getArea() {
				return area;
		}

		public void setArea(String area) {
				this.area = area;
		}

		public String getDetail() {
				return detail;
		}

		public void setDetail(String detail) {
				this.detail = detail;
		}

		public String getUid() {
				return uid;
		}

		public void setUid(String uid) {
				this.uid = uid;
		}

		@Override
		public String toString() {
				return "Result{" +
								"name='" + name + '\'' +
								", location=" + location +
								", address='" + address + '\'' +
								", province='" + province + '\'' +
								", city='" + city + '\'' +
								", area='" + area + '\'' +
								", detail='" + detail + '\'' +
								", uid='" + uid + '\'' +
								'}';
		}
}
