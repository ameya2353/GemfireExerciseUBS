package com.ubs.exercise.client.converter;

public interface IConverterWithStrategy extends IConverter{

    /*
     * sometimes conversion strategy is required to tell the converted how it is expected to read the data.
     * @param strategy -> type: BaseMapper | the strategy must extend BaseMapper, so it can be used by the converter.
     * Currently only CSV requires this feature, all other converters have inbuild conversion strategies. this is cause
     * only csv has linear data, ie it is unstructured and java is not able to do 1-to-1 mapping of the data.
     * */
    public void setConverterStrategy(BaseMapper strategy) throws Exception;
}
