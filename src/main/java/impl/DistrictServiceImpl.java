package impl;

import entity.District;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.DistrictRepository;
import service.DistrictService;

@Service("districtService")
public class DistrictServiceImpl implements DistrictService {
    private final DistrictRepository districtRepository;

    @Autowired
    public DistrictServiceImpl(DistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
    }

    @Override
    public District getDistrictById(int districtId) {
        return districtRepository.findDistrictByDistrictId(districtId);
    }

    @Override
    public District createDistrict(District district) {
         return districtRepository.save(district);
    }

    @Override
    public District updateDistrict(District district) {
        return districtRepository.save(district);
    }

    @Override
    public boolean deleteDistrict(District district) {
        districtRepository.delete(district);
        return true;
    }
}
