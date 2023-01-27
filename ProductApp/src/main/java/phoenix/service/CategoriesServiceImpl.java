package phoenix.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import phoenix.domain.Categories;
import phoenix.repository.CategoriesRepository;

import java.util.List;

/**
 * User Khan, C M Abdullah
 * Ref:
 */
@Service
public class CategoriesServiceImpl implements CategoriesService {
	
	private final CategoriesRepository categoriesRepository;
	
	@Autowired
	public CategoriesServiceImpl(CategoriesRepository categoriesRepository){
		this.categoriesRepository = categoriesRepository;
	}
	
	@Override
	public void saveAll(List<Categories> list) {
		categoriesRepository.saveAll(list);
	}
}
