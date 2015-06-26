package suncertify.db.datasource;

public interface DataSourceFactory {

	DataSourceReader getDatoSourceReader();

	DataSourceWriter getDatoSourceWritter();

}
