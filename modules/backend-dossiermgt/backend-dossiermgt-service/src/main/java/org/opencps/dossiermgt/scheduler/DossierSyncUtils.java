package org.opencps.dossiermgt.scheduler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;

public class DossierSyncUtils {

	public static final String dossierId = "dossierId";
	public static final String dossierSyncId = "dossierSyncId";
	public static final String method = "method";
	public static ArrayList<DossierSyncOrderedModel> convertToModel(JSONArray array) {
		ArrayList<DossierSyncOrderedModel> dossierSyncModels = new ArrayList<DossierSyncOrderedModel>();

		for (int i = 0; i < array.length(); i++) {
			JSONObject jsElm = array.getJSONObject(i);

			DossierSyncOrderedModel dsm = new DossierSyncOrderedModel(jsElm.getLong(dossierSyncId),
					jsElm.getLong(dossierId), jsElm.getInt(method));
			
			dossierSyncModels.add(dsm);
		}
		
		return dossierSyncModels;
	}

	public static void orderSync(ArrayList<DossierSyncOrderedModel> origin) {

		Collections.sort(origin, new Comparator<DossierSyncOrderedModel>() {
			@Override
			public int compare(DossierSyncOrderedModel obj1, DossierSyncOrderedModel obj2) {

				Long dossierId1 = obj1.getDossierId();

				Long dossierId2 = obj2.getDossierId();

				int didComp = dossierId1.compareTo(dossierId2);

				if (didComp != 0) {
					return didComp;
				} else {
					Integer method1 = obj1.getMethodId();

					Integer method2 = obj2.getMethodId();

					return method2.compareTo(method1);
				}

			}
		});

	}

}
