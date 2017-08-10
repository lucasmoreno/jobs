RSpec.describe ::Models::Charge do
  it { expect(described_class).to have_attribute(:amount).of_type(Float) }
  it { expect(described_class).to have_attribute(:card).of_type(::Models::Card) }
  it { expect(described_class).to have_attribute(:card_id).of_type(String) }
  it { expect(described_class).to have_attribute(:id).of_type(String) }
  it { expect(described_class).to have_attribute(:intermediaries).of_type(Array, member_type: ::Models::Intermediary) }

  subject(:model) do
    described_class.new(
      id: id,
      card_id: card_id,
      amount: amount,
      card: card,
      intermediaries: intermediaries
    )
  end
  let(:id) { 'le_id' }
  let(:card_id) { 'le_card_id' }
  let(:amount) { 1.54 }
  let(:card) { FactoryGirl.build :card }
  let(:intermediaries) { [intermediary] }
  let(:intermediary) { FactoryGirl.build :intermediary }

  describe '#to_h' do
    subject { model.to_h }

    let(:hash) do
      {
        id: id,
        card_id: card_id,
        amount: amount,
        intermediaries: [intermediary_to_h]
      }
    end
    let(:intermediary_to_h) {  { key: :value } }

    before do
      allow(intermediary).to receive(:to_h).and_return(intermediary_to_h)
    end

    it { is_expected.to eq hash }
  end
end
