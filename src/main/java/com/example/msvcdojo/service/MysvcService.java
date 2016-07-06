package com.example.msvcdojo.service;

import com.example.msvcdojo.model.OperationError;

import java.util.List;

/**
 * Created by Josep Maria on 06/07/2016.
 */
public interface MysvcService {
    List<OperationError> findOperationError();

    List<OperationError> findById(String id);
}
