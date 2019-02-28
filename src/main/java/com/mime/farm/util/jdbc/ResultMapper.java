package com.mime.farm.util.jdbc;

import java.util.Map;

/**
 * 类型转化接口
 * @author PengChan
 * @param <T>
 */
public interface ResultMapper<T> {
	@SuppressWarnings("hiding")
	<T> T convert(Map<String, Object> resource, Class<T> c);
}
