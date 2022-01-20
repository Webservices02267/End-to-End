package dtu.services.Entities;

public class PaymentDTO {

	public String token;
	public String merchant;
	public int amount;
	public String description;

	public PaymentDTO(String token, String merchant, int amount2, String description) {
		this.token = token;
		this.merchant = merchant;
		this.amount = amount2;
		this.description = description;
	}

	public PaymentDTO() {

	}


	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getMerchant() {
		return merchant;
	}

	public void setMerchant(String merchant) {
		this.merchant = merchant;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "PaymentDTO [amount=" + amount + ", description=" + description + ", merchant=" + merchant + ", token="
				+ token + "]";
	}
}
