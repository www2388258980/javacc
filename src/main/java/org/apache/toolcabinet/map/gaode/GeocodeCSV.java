package org.apache.toolcabinet.map.gaode;


public class GeocodeCSV {

		private String organizationId;


		private String name;

		private GeocodeResponse data;

		public String getOrganizationId() {
				return organizationId;
		}

		public void setOrganizationId(String organizationId) {
				this.organizationId = organizationId;
		}

		public String getName() {
				return name;
		}

		public void setName(String name) {
				this.name = name;
		}

		public GeocodeResponse getData() {
				return data;
		}

		public void setData(GeocodeResponse data) {
				this.data = data;
		}
}
