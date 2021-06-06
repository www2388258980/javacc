package org.apache.toolcabinet.map.baidu;

public class Location {
		// 经度
		private String lat;
		// 纬度
		private String lng;

		public String getLat() {
				return lat;
		}

		public void setLat(String lat) {
				this.lat = lat;
		}

		public String getLng() {
				return lng;
		}

		public void setLng(String lng) {
				this.lng = lng;
		}

		@Override
		public String toString() {
				return "Location{" +
								"lat='" + lat + '\'' +
								", lng='" + lng + '\'' +
								'}';
		}
}
