package dtu.services.Entities;

public class PaymentMerchant {
	private String merchantId;
	private String token;
	private String amount;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((merchantId == null) ? 0 : merchantId.hashCode());
		result = prime * result + ((token == null) ? 0 : token.hashCode());
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
		PaymentMerchant other = (PaymentMerchant) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (merchantId == null) {
			if (other.merchantId != null)
				return false;
		} else if (!merchantId.equals(other.merchantId))
			return false;
		if (token == null) {
			if (other.token != null)
				return false;
		} else if (!token.equals(other.token))
			return false;
		return true;
	}

	public PaymentMerchant(Payment payment) {
		this.merchantId = payment.getMerchantId();
		this.token = payment.getToken();
		this.amount = payment.getAmount();
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("PaymentMerchant{");
		sb.append("merchantId='").append(merchantId).append('\'');
		sb.append(", token='").append(token).append('\'');
		sb.append(", amount='").append(amount).append('\'');
		sb.append('}');
		return sb.toString();
	}

	public String getMerchantId() {
		return merchantId;
	}

	public String getToken() {
		return token;
	}

	public String getAmount() {
		return amount;
	}
}
