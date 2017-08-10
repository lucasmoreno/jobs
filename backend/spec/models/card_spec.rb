RSpec.describe ::Models::Card do
  it { expect(described_class).to have_attribute(:expiration_month).of_type(String) }
  it { expect(described_class).to have_attribute(:expiration_year).of_type(String) }
  it { expect(described_class).to have_attribute(:cvv).of_type(String) }
  it { expect(described_class).to have_attribute(:holder).of_type(String) }
  it { expect(described_class).to have_attribute(:number).of_type(String) }

  it { is_expected.to validate_presence_of(:cvv) }
  it { is_expected.to validate_presence_of(:expiration_month) }
  it { is_expected.to validate_presence_of(:expiration_year) }
  it { is_expected.to validate_presence_of(:holder) }
  it { is_expected.to validate_presence_of(:number) }
end
