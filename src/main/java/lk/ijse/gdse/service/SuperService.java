package lk.ijse.gdse.service;


import lk.ijse.gdse.dto.SuperDto;

public interface SuperService<T extends SuperDto> {
    boolean save(T dto);

    boolean update(T dto);

    T view(T dto);

    boolean delete(T dto);
}
