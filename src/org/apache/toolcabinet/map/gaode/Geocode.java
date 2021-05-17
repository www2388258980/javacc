package org.apache.toolcabinet.map.gaode;

import java.io.Serializable;

public class Geocode implements Serializable {
		// 结构化地址信息
		private String formatted_address;

		// 国家
		private String country;

		// 地址所在的省份名
		private String province;

		// 地址所在的城市名
		private String city;

		// 城市编码
		private String citycode;

		// 地址所在的区
		private String district;

		// 街道
		private String street;

		// 门牌
		private String number;

		// 区域编码
		private String adcode;

		// 坐标点
		private String location;

		// 匹配级别
		private String level;

		public String getFormatted_address() {
				return formatted_address;
		}

		public void setFormatted_address(String formatted_address) {
				this.formatted_address = formatted_address;
		}

		public String getCountry() {
				return country;
		}

		public void setCountry(String country) {
				this.country = country;
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

		public String getCitycode() {
				return citycode;
		}

		public void setCitycode(String citycode) {
				this.citycode = citycode;
		}

		public String getDistrict() {
				return district;
		}

		public void setDistrict(String district) {
				this.district = district;
		}

		public String getStreet() {
				return street;
		}

		public void setStreet(String street) {
				this.street = street;
		}

		public String getNumber() {
				return number;
		}

		public void setNumber(String number) {
				this.number = number;
		}

		public String getAdcode() {
				return adcode;
		}

		public void setAdcode(String adcode) {
				this.adcode = adcode;
		}

		public String getLocation() {
				return location;
		}

		public void setLocation(String location) {
				this.location = location;
		}

		public String getLevel() {
				return level;
		}

		public void setLevel(String level) {
				this.level = level;
		}

		@Override
		public String toString() {
				return "Geocode{" +
								"formatted_address='" + formatted_address + '\'' +
								", country='" + country + '\'' +
								", province='" + province + '\'' +
								", city='" + city + '\'' +
								", citycode='" + citycode + '\'' +
								", district='" + district + '\'' +
								", street='" + street + '\'' +
								", number='" + number + '\'' +
								", adcode='" + adcode + '\'' +
								", location='" + location + '\'' +
								", level='" + level + '\'' +
								'}';
		}
}
