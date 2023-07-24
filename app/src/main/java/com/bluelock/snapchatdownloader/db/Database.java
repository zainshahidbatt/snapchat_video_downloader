package com.bluelock.snapchatdownloader.db;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;

import com.bluelock.snapchatdownloader.models.FVideo;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Database {
    private static final String TAG = "Database";
    private static Context context;
    private static int listSize = 0;
    private static DbHelper dbHelper;
    private static Database instance;
    private LoadCallback callback;

    private Database() {
    }

    public static Database init(Context context1) {
        context = context1;
        dbHelper = new DbHelper(context1);

        if (instance == null) {
            instance = new Database();
        }

        return instance;
    }

    public void setCallback(LoadCallback callback1) {
        callback = callback1;
    }

    /**
     * Add a video to the paper db
     *
     * @param video downloaded video
     */
    public void addVideo(FVideo video) {
        /*ArrayList<FVideo> videos = Paper.book().read(KEY_VIDEOS, new ArrayList<>());
        videos.add(video);
        Paper.book().write(KEY_VIDEOS, videos);*/

        /*dbHelper.insertVideo(video);
        Log.d(TAG, "addVideo: video added video title " + video.getFileName());*/
        /*if (callback != null)
            callback.onUpdateDatabase();*/

        /*AppExecutors.getInstance().diskIO().execute(() -> {
            dbHelper.insertVideo(video);
            Log.d(TAG, "addVideo: video added video title " + video.getFileName());
            if (callback!= null)
                callback.onUpdateDatabase();

        });*/

        new AddVideoAsync().execute(video);
    }

    /**
     * get all video
     *
     * @return the list of videos
     */
    public ArrayList<FVideo> getVideos() {
        /* ArrayList<FVideo> videos = Paper.book().read(KEY_VIDEOS, new ArrayList<>());

        if (videos == null)
            return null;
        Log.d(TAG, "getVideos: number of video " + videos.size());
        Collections.reverse(videos);*/

        ArrayList<FVideo> result;
        try {
            result = new loadAllVideosTask().execute().get();
            listSize = result.size();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public ArrayList<FVideo> getRecentVideos() {
        ArrayList<FVideo> result = null;
        try {
            result = new loadRecentVideosTask().execute().get();
            listSize = result.size();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public ArrayList<FVideo> getFacebookVideos() {
        ArrayList<FVideo> result;
        try {
            result = new loadFacebookVideosTask().execute().get();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * update the state
     * called when a video is in download state or going to processing state
     * or processing to complete state
     *
     * @param downloadId video download id
     * @param state      downloading, processing, complete
     */
    public void updateState(long downloadId, int state) {
        new UpdateStateAsync().execute(new Pair<>(downloadId, state));
    }

    /**
     * setting the file uri location
     * called when download is complete
     *
     * @param downloadId video download id
     * @param uri        file location
     */
    public void setUri(long downloadId, String uri) {
        new SetUriAsync().execute(new Pair<>(downloadId, uri));


    }

    /**
     * @param downloadId video download id
     * @return a video according have that download id
     */
    public FVideo getVideo(long downloadId) {

        FVideo result;
        try {
            result = new loadVideoTask().execute(downloadId).get();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * Delete a video instance form the paper db also the download list
     * but will not download form the file
     *
     * @param videoId video download id
     */
    public void deleteAVideo(Long videoId) {

        /*dbHelper.deleteVideo(videoId);
        if (callback != null)
            callback.onUpdateDatabase();*/

        new DeleteAsync().execute(videoId);


    }

    public interface LoadCallback {
        void onUpdateDatabase();
    }

    static class loadAllVideosTask extends AsyncTask<Void, Void, ArrayList<FVideo>> {

        @Override
        protected ArrayList<FVideo> doInBackground(Void... voids) {
            Log.d(TAG, "doInBackground: get videos");
            return dbHelper.getAllVideos();
        }
    }

    static class loadRecentVideosTask extends AsyncTask<Void, Void, ArrayList<FVideo>> {

        @Override
        protected ArrayList<FVideo> doInBackground(Void... voids) {
            return dbHelper.getRecentVideos();
        }
    }

    static class loadFacebookVideosTask extends AsyncTask<Void, Void, ArrayList<FVideo>> {

        @Override
        protected ArrayList<FVideo> doInBackground(Void... voids) {
            ArrayList<FVideo> videos = dbHelper.getFacebookVideos();
            Log.d(TAG, "doInBackground: video size " + videos.size());
            return videos;
        }
    }

    static class loadVideoTask extends AsyncTask<Long, Void, FVideo> {

        @Override
        protected FVideo doInBackground(Long... longs) {
            Log.d(TAG, "doInBackground: getVideo");
            return dbHelper.getVideo(longs[0]);
        }
    }

    class AddVideoAsync extends AsyncTask<FVideo, Void, Void> {

        @Override
        protected Void doInBackground(FVideo... fVideos) {
            dbHelper.insertVideo(fVideos[0], listSize);
            Log.d(TAG, "addVideo: ");
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            if (callback != null)
                callback.onUpdateDatabase();
        }
    }

    class UpdateStateAsync extends AsyncTask<Pair<Long, Integer>, Void, Void> {

        @Override
        protected Void doInBackground(Pair<Long, Integer>... pairs) {
            Log.d(TAG, "doInBackground: update state");
            dbHelper.updateState(pairs[0].first, pairs[0].second);
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            if (callback != null)
                callback.onUpdateDatabase();
        }
    }

    class SetUriAsync extends AsyncTask<Pair<Long, String>, Void, Void> {

        @Override
        protected Void doInBackground(Pair<Long, String>... pairs) {
            dbHelper.setUri(pairs[0].first, pairs[0].second);
            Log.d(TAG, "doInBackground: set uri");
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            if (callback != null)
                callback.onUpdateDatabase();
        }
    }

    class DeleteAsync extends AsyncTask<Long, Void, Void> {

        @Override
        protected Void doInBackground(Long... longs) {
            dbHelper.deleteVideo(longs[0]);
            Log.d(TAG, "doInBackground: delete video");
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            if (callback != null)
                callback.onUpdateDatabase();
        }
    }
}
