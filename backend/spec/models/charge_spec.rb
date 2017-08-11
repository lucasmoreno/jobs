RSpec.describe ::Models::Charge do
  it { expect(described_class).to have_attribute(:amount).of_type(Float) }
  it { expect(described_class).to have_attribute(:card).of_type(::Models::Card) }
  it { expect(described_class).to have_attribute(:card_id).of_type(String) }
  it { expect(described_class).to have_attribute(:id).of_type(String) }
  it { expect(described_class).to have_attribute(:intermediaries).of_type(Array, member_type: ::Models::Intermediary) }

  it { is_expected.to validate_presence_of(:amount) }
  it { is_expected.to validate_numericality_of(:amount).is_greater_than_or_equal_to(0) }

  subject(:model) do
    described_class.new(
      id: id,
      card_id: card_id,
      amount: amount,
      card: card,
      intermediaries: intermediaries,
      inserted_at: inserted_at,
      updated_at: updated_at
    )
  end
  let(:id) { 'le_id' }
  let(:card_id) { 'le_card_id' }
  let(:amount) { 1.54 }
  let(:card) { FactoryGirl.build :card }
  let(:intermediaries) { [intermediary] }
  let(:intermediary) { FactoryGirl.build :intermediary }
  let(:inserted_at) { Time.now }
  let(:updated_at) { Time.now }

  describe '#calculate_intermediaries_amounts!' do
    subject { model.calculate_intermediaries_amounts! }

    before do
      allow(intermediary).to receive(:calculate_amount!).with(charge_amount: amount)
    end

    it do
      subject
      expect(intermediary).to have_received(:calculate_amount!).with(charge_amount: amount).once
    end
  end

  describe '#to_h' do
    subject { model.to_h }

    let(:hash) do
      {
        id: id,
        card_id: card_id,
        amount: amount,
        intermediaries: [intermediary_to_h],
        inserted_at: inserted_at,
        updated_at: updated_at
      }
    end
    let(:intermediary_to_h) {  { key: :value } }

    before do
      allow(intermediary).to receive(:to_h).and_return(intermediary_to_h)
    end

    it { is_expected.to eq hash }
  end
end
