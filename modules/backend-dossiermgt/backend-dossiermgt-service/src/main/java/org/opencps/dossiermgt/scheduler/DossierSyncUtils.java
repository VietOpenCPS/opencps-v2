package org.opencps.dossiermgt.scheduler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DossierSyncUtils {

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
