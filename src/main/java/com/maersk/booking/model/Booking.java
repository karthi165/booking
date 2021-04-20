package com.maersk.booking.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import com.maersk.booking.common.ContainerType;
import com.maersk.booking.model.validator.AllowableValues;

@Table(value = "booking")
public class Booking implements Serializable {

	private static final long serialVersionUID = 4865177904758534071L;

	@PrimaryKey
	private String bookingReference;

	@AllowableValues(value = "20,40", message = "Container size should be either 20 or 40")
	private Integer containerSize;

	@CassandraType(type = CassandraType.Name.VARCHAR)
	private ContainerType containerType;

	@Size(min = 5, max = 20, message = "Orgin city name should be 5-20 characters long.")
	private String orgin;

	@Size(min = 5, max = 20, message = "Destination city name should be 5-20 characters long.")
	private String destintation;

	@Max(value = 100, message = "Quanity should not be greater than 100.")
	@Min(value = 1, message = "Quanity should not be lesser than 1.")
	private Integer quantity;

	private String timestamp;

	public Booking(Builder builder) {
		this.setContainerSize(builder.containerSize);
		this.setContainerType(builder.containerType);
		this.setDestintation(builder.destintation);
		this.setOrgin(builder.orgin);
		this.setQuantity(builder.quantity);
	}

	public static Builder getBuilder() {
		return new Builder();

	}
	
	

	public Booking() {
	}

	public String getBookingReference() {
		return bookingReference;
	}

	public void setBookingReference(String bookingReference) {
		this.bookingReference = bookingReference;
	}

	public Integer getContainerSize() {
		return containerSize;
	}

	public void setContainerSize(Integer containerSize) {
		this.containerSize = containerSize;
	}

	public ContainerType getContainerType() {
		return containerType;
	}

	public void setContainerType(ContainerType containerType) {
		this.containerType = containerType;
	}

	public String getOrgin() {
		return orgin;
	}

	public void setOrgin(String orgin) {
		this.orgin = orgin;
	}

	public String getDestintation() {
		return destintation;
	}

	public void setDestintation(String destintation) {
		this.destintation = destintation;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timeStamp) {
		this.timestamp = timeStamp;
	}

	// Builder
	public static class Builder {
		private Integer containerSize;
		private ContainerType containerType;
		private String orgin;
		private String destintation;
		private Integer quantity;
		private String timestamp;

		public Builder containerSize(Integer containerSize) {
			this.containerSize = containerSize;
			return this;
		}

		public Builder containerType(ContainerType containerType) {
			this.containerType = containerType;
			return this;
		}

		public Builder orgin(String orgin) {
			this.orgin = orgin;
			return this;
		}

		public Builder destintation(String destintation) {
			this.destintation = destintation;
			return this;
		}

		public Builder quantity(Integer quantity) {
			this.quantity = quantity;
			return this;
		}

		public Booking build() {
			return new Booking(this);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookingReference == null) ? 0 : bookingReference.hashCode());
		result = prime * result + ((containerSize == null) ? 0 : containerSize.hashCode());
		result = prime * result + ((containerType == null) ? 0 : containerType.hashCode());
		result = prime * result + ((destintation == null) ? 0 : destintation.hashCode());
		result = prime * result + ((orgin == null) ? 0 : orgin.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
		result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Booking other = (Booking) obj;
		if (bookingReference == null) {
			if (other.bookingReference != null)
				return false;
		} else if (!bookingReference.equals(other.bookingReference))
			return false;
		if (containerSize == null) {
			if (other.containerSize != null)
				return false;
		} else if (!containerSize.equals(other.containerSize))
			return false;
		if (containerType != other.containerType)
			return false;
		if (destintation == null) {
			if (other.destintation != null)
				return false;
		} else if (!destintation.equals(other.destintation))
			return false;
		if (orgin == null) {
			if (other.orgin != null)
				return false;
		} else if (!orgin.equals(other.orgin))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		return true;
	}
	
}
