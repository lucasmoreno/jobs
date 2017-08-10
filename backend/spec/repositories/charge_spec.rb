RSpec.describe ::Repositories::Charge do
  describe '.create' do
    subject { described_class.create(charge) }

    let(:charge) { FactoryGirl.build :charge }
    let(:uuid) { "le_uuid" }
    let(:intermediary) { subject.intermediaries.first }
    let(:intermediary_amount) do
      charge.amount * intermediary.fee + intermediary.flat
    end

    before do
      allow(SecureRandom).to receive(:uuid).and_return(uuid)
    end

    it { expect(subject.id).to eq(uuid) }
    it { expect(subject.card_id).to eq(uuid) }
    it { expect(intermediary.amount).to eq(intermediary_amount) }
  end
end
