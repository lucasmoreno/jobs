RSpec.describe ::Models::Intermediary do
  it { expect(described_class).to have_attribute(:amount).of_type(Float) }
  it { expect(described_class).to have_attribute(:description).of_type(String) }
  it { expect(described_class).to have_attribute(:fee).of_type(Float) }
  it { expect(described_class).to have_attribute(:flat).of_type(Float) }

  it { is_expected.to validate_presence_of(:description) }
  it { is_expected.to validate_numericality_of(:fee).is_less_than_or_equal_to(1) }
  it { is_expected.to validate_numericality_of(:fee).is_greater_than_or_equal_to(0) }
  it { is_expected.to validate_numericality_of(:flat).is_greater_than_or_equal_to(0) }
  it { is_expected.to validate_numericality_of(:amount).is_greater_than_or_equal_to(0) }

  subject(:model) do
    described_class.new(
      description: description,
      fee: fee,
      flat: flat
    )
  end
  let(:description) { 'le description' }
  let(:fee) { 0.54 }
  let(:flat) { 2 }

  describe '#calculate_amount!' do
    subject { model.calculate_amount!(charge_amount: charge_amount) }
    let(:charge_amount) { 12 }
    let(:calculated_amount) { charge_amount * fee + flat }

    it { is_expected.to eq calculated_amount }

    it 'sets the amount attribute' do
      subject
      expect(model.amount).to eq calculated_amount
    end
  end
end
