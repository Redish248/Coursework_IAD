package impl;

import entity.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.PriceRepository;
import service.PriceService;

@Service("priceService")
public class PriceServiceImpl implements PriceService {

    final PriceRepository priceRepository;

    @Autowired
    public PriceServiceImpl(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public Price getPriceById(int priceId) {
        return priceRepository.findPriceByPriceId(priceId);
    }

    @Override
    public Price getPriceByName(String name){
        return priceRepository.findPriceByName(name);
    }

    @Override
    public Price createPrice(Price price) {
        return priceRepository.save(price);
    }

    @Override
    public boolean deletePrices(Price price) {
        priceRepository.delete(price);
        return true;
    }

    @Override
    public Price updatePrices(Price price) {
        return priceRepository.save(price);
    }
}
