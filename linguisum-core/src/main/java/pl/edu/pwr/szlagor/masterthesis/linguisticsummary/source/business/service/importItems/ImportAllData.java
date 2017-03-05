package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.importItems;

import java.io.FileNotFoundException;

/**
 * Created by Paweł on 2017-02-11.
 */
public class ImportAllData {

    public static void main(String[] args) throws FileNotFoundException {
        importAllData();
    }

    public static void importAllData() throws FileNotFoundException {
        ImportDeviceData.importData();
        ImportPersonsData.importData();
        ImportRoomsData.importData();
        //ImportWeatherData.importData();
    }

}
