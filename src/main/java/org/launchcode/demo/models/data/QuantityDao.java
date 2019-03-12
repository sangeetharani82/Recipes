package org.launchcode.demo.models.data;

import org.launchcode.demo.models.Quantity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface QuantityDao extends CrudRepository<Quantity, Integer> {
}
