package com.ubs.exercise.client.converter.csv;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.ubs.exercise.client.converter.BaseMapper;

/*
* Mapper for CSV files
* Each Domain object needs to have a CSV mapper and to create one this class needs to be extended.
* */

public class Mapper extends ColumnPositionMappingStrategy implements BaseMapper {
}
