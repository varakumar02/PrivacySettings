package org.aox.privacy;

import static android.provider.SearchIndexablesContract.COLUMN_INDEX_XML_RES_CLASS_NAME;
import static android.provider.SearchIndexablesContract.COLUMN_INDEX_XML_RES_ICON_RESID;
import static android.provider.SearchIndexablesContract.COLUMN_INDEX_XML_RES_INTENT_ACTION;
import static android.provider.SearchIndexablesContract.COLUMN_INDEX_XML_RES_INTENT_TARGET_CLASS;
import static android.provider.SearchIndexablesContract.COLUMN_INDEX_XML_RES_INTENT_TARGET_PACKAGE;
import static android.provider.SearchIndexablesContract.COLUMN_INDEX_XML_RES_RANK;
import static android.provider.SearchIndexablesContract.COLUMN_INDEX_XML_RES_RESID;
import static android.provider.SearchIndexablesContract.INDEXABLES_RAW_COLUMNS;
import static android.provider.SearchIndexablesContract.INDEXABLES_XML_RES_COLUMNS;
import static android.provider.SearchIndexablesContract.NON_INDEXABLES_KEYS_COLUMNS;

import android.database.Cursor;
import android.database.MatrixCursor;
import android.provider.SearchIndexablesProvider;

/** Lets Settings search find the entries of privacy.xml and open PrivacyActivity on them. */
public final class SearchProvider extends SearchIndexablesProvider {
    @Override
    public boolean onCreate() {
        return true;
    }

    @Override
    public Cursor queryXmlResources(String[] projection) {
        final MatrixCursor cursor = new MatrixCursor(INDEXABLES_XML_RES_COLUMNS);
        final Object[] row = new Object[INDEXABLES_XML_RES_COLUMNS.length];
        row[COLUMN_INDEX_XML_RES_RANK] = 1;
        row[COLUMN_INDEX_XML_RES_RESID] = R.xml.privacy;
        row[COLUMN_INDEX_XML_RES_CLASS_NAME] = null;
        row[COLUMN_INDEX_XML_RES_ICON_RESID] = 0;
        row[COLUMN_INDEX_XML_RES_INTENT_ACTION] = "com.android.settings.action.EXTRA_SETTINGS";
        row[COLUMN_INDEX_XML_RES_INTENT_TARGET_PACKAGE] = getContext().getPackageName();
        row[COLUMN_INDEX_XML_RES_INTENT_TARGET_CLASS] = PrivacyActivity.class.getName();
        cursor.addRow(row);
        return cursor;
    }

    @Override
    public Cursor queryRawData(String[] projection) {
        return new MatrixCursor(INDEXABLES_RAW_COLUMNS);
    }

    @Override
    public Cursor queryNonIndexableKeys(String[] projection) {
        return new MatrixCursor(NON_INDEXABLES_KEYS_COLUMNS);
    }
}
