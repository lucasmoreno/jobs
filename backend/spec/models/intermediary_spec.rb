RSpec.describe ::Models::Intermediary do
  it { expect(described_class).to have_attribute(:amount).of_type(Float) }
  it { expect(described_class).to have_attribute(:description).of_type(String) }
  it { expect(described_class).to have_attribute(:fee).of_type(Float) }
  it { expect(described_class).to have_attribute(:flat).of_type(Float) }

  it { is_expected.to validate_presence_of(:description) }
  it { is_expected.to validate_presence_of(:fee) }
  it { is_expected.to validate_presence_of(:flat) }
end
