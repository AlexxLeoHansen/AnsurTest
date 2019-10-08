package com.example.joseba.ansurtest;

import android.util.Base64;
import android.util.JsonReader;
import android.util.JsonToken;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import javax.net.ssl.HttpsURLConnection;

public class HttpClient {

    //API credentials vars
    private static final String API_URL = "https://thor.ansur.no/api";
    private static final String AUTH = "testcandidate:bygcd33";
    //createConn vars
    private static String conn;
    private static int rCode;
    private static InputStream responseBody;
    private static String line;
    //getData vars
    private static InputStreamReader responseBodyReader;
    //JsonData
    private static ArrayList<Thumbnail> jsonData;

    //Create Https connection and error handling (UPDATE: NOW JUST LOAD JSON DATA LOCALLY)
    public static void createConn() throws IOException, MalformedURLException {
        //conn = (HttpsURLConnection) new URL(API_URL+"/photos.json").openConnection();
        conn = new String("{\n" +
                "    \"data\": {\n" +
                "        \"photos\": [\n" +
                "            {\n" +
                "                \"id\": 19741,\n" +
                "                \"priority\": 0,\n" +
                "                \"signature\": \"294e6d6fc1168cdda7ed3928feb6fa5a\",\n" +
                "                \"instant\": \"2014-05-14 04:34:08\",\n" +
                "                \"created\": \"2016-05-05 16:48:55.065\",\n" +
                "                \"updated\": \"2019-07-05 12:03:25.58166\",\n" +
                "                \"caption\": \"Uploaded on 2016-05-05\",\n" +
                "                \"meta\": {\n" +
                "                    \"Source\": {\n" +
                "                        \"size\": 206211,\n" +
                "                        \"camera_make\": \"NIKON CORPORATION\",\n" +
                "                        \"filename\": \"051514.N.AD_.RIGEMERGENCY 1.jpg\",\n" +
                "                        \"camera_model\": \"NIKON D7000\",\n" +
                "                        \"height\": 662,\n" +
                "                        \"width\": 1000\n" +
                "                    }\n" +
                "                },\n" +
                "                \"version\": \"572b60a20f484185bc4901990a00020f\",\n" +
                "                \"location_latitude\": 50.824183125196,\n" +
                "                \"location_longitude\": -0.1754808425659,\n" +
                "                \"location_time\": \"2016-05-05 17:02:58\",\n" +
                "                \"location_accuracy\": null,\n" +
                "                \"location_altitude\": null,\n" +
                "                \"location_provider\": \"manual\",\n" +
                "                \"mission_id\": 200,\n" +
                "                \"mission_name\": \"Test\",\n" +
                "                \"thumbnail_hash\": \"468fce5e091aa5f85292a689071e628f\",\n" +
                "                \"preview_hash\": \"86a04285c405a1512899958ab3f0a1d8\",\n" +
                "                \"category_id\": null,\n" +
                "                \"category_name\": null,\n" +
                "                \"user_name\": \"Test Candidate\",\n" +
                "                \"user_id\": 166,\n" +
                "                \"user_username\": \"testcandidate\",\n" +
                "                \"user_color\": \"FFFFFF\",\n" +
                "                \"observation_url\": \"https://thor.ansur.no/signs/processing/19741\",\n" +
                "                \"preview_url\": \"https://thor.ansur.no/display/file/86a04285c405a1512899958ab3f0a1d8/testcandidate/294e6d6fc1168cdda7ed3928feb6fa5a/preview18886.webp.jpg\",\n" +
                "                \"thumbnail_url\": \"https://thor.ansur.no/display/file/468fce5e091aa5f85292a689071e628f/testcandidate/294e6d6fc1168cdda7ed3928feb6fa5a/thumbnail.jpg\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": 19750,\n" +
                "                \"priority\": 0,\n" +
                "                \"signature\": \"0467672ed1fe8ebf07c0ec25fb5589ed\",\n" +
                "                \"instant\": \"2012-06-10 11:31:24\",\n" +
                "                \"created\": \"2016-05-05 17:00:25.377\",\n" +
                "                \"updated\": \"2016-05-12 15:40:29.296525\",\n" +
                "                \"caption\": \"Uploaded on 2016-05-05\",\n" +
                "                \"meta\": {\n" +
                "                    \"Source\": {\n" +
                "                        \"size\": 2453524,\n" +
                "                        \"camera_make\": \"FUJIFILM\",\n" +
                "                        \"filename\": \"D.River_.beach_.Rescue.5.jpg\",\n" +
                "                        \"camera_model\": \"FinePix S4000\",\n" +
                "                        \"height\": 1728,\n" +
                "                        \"width\": 3072\n" +
                "                    }\n" +
                "                },\n" +
                "                \"version\": \"573487cd51704579bfb75c6a0a00020f\",\n" +
                "                \"location_latitude\": -33.776864373768,\n" +
                "                \"location_longitude\": 151.31195066252,\n" +
                "                \"location_time\": \"2016-05-12 15:40:29\",\n" +
                "                \"location_accuracy\": null,\n" +
                "                \"location_altitude\": null,\n" +
                "                \"location_provider\": \"manual\",\n" +
                "                \"mission_id\": null,\n" +
                "                \"mission_name\": null,\n" +
                "                \"thumbnail_hash\": \"3453049e26f617799acfa4a7e5d4a5af\",\n" +
                "                \"preview_hash\": \"c075c0985da3a50c19875eb16e7c9bd5\",\n" +
                "                \"category_id\": null,\n" +
                "                \"category_name\": null,\n" +
                "                \"user_name\": \"Test Candidate\",\n" +
                "                \"user_id\": 166,\n" +
                "                \"user_username\": \"testcandidate\",\n" +
                "                \"user_color\": \"FFFFFF\",\n" +
                "                \"observation_url\": \"https://thor.ansur.no/signs/processing/19750\",\n" +
                "                \"preview_url\": \"https://thor.ansur.no/display/file/c075c0985da3a50c19875eb16e7c9bd5/testcandidate/0467672ed1fe8ebf07c0ec25fb5589ed/preview16340.webp.jpg\",\n" +
                "                \"thumbnail_url\": \"https://thor.ansur.no/display/file/3453049e26f617799acfa4a7e5d4a5af/testcandidate/0467672ed1fe8ebf07c0ec25fb5589ed/thumbnail.jpg\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": 19747,\n" +
                "                \"priority\": 0,\n" +
                "                \"signature\": \"16507e62d429ee2414995f6e83a2c268\",\n" +
                "                \"instant\": \"2016-05-05 16:57:32\",\n" +
                "                \"created\": \"2016-05-05 16:57:29.605\",\n" +
                "                \"updated\": \"2016-05-08 15:49:40.478759\",\n" +
                "                \"caption\": \"Uploaded on 2016-05-05\",\n" +
                "                \"meta\": {\n" +
                "                    \"Source\": {\n" +
                "                        \"size\": 109027,\n" +
                "                        \"camera_make\": null,\n" +
                "                        \"filename\": \"01nepalexplainer.adapt.768.1.jpg\",\n" +
                "                        \"camera_model\": null,\n" +
                "                        \"height\": 511,\n" +
                "                        \"width\": 768\n" +
                "                    }\n" +
                "                },\n" +
                "                \"version\": \"572f43f4b2c844ecb3c356820a00020f\",\n" +
                "                \"location_latitude\": 27.702992585664,\n" +
                "                \"location_longitude\": 85.34179686312,\n" +
                "                \"location_time\": \"2016-05-08 15:49:40\",\n" +
                "                \"location_accuracy\": null,\n" +
                "                \"location_altitude\": null,\n" +
                "                \"location_provider\": \"manual\",\n" +
                "                \"mission_id\": null,\n" +
                "                \"mission_name\": null,\n" +
                "                \"thumbnail_hash\": \"b7a2a5a640bcee74f9ccb9434cee8aa5\",\n" +
                "                \"preview_hash\": \"00504cd15acc8d2678e78b549c2da6bd\",\n" +
                "                \"category_id\": null,\n" +
                "                \"category_name\": null,\n" +
                "                \"user_name\": \"Test Candidate\",\n" +
                "                \"user_id\": 166,\n" +
                "                \"user_username\": \"testcandidate\",\n" +
                "                \"user_color\": \"FFFFFF\",\n" +
                "                \"observation_url\": \"https://thor.ansur.no/signs/processing/19747\",\n" +
                "                \"preview_url\": \"https://thor.ansur.no/display/file/00504cd15acc8d2678e78b549c2da6bd/testcandidate/16507e62d429ee2414995f6e83a2c268/preview16110.webp.jpg\",\n" +
                "                \"thumbnail_url\": \"https://thor.ansur.no/display/file/b7a2a5a640bcee74f9ccb9434cee8aa5/testcandidate/16507e62d429ee2414995f6e83a2c268/thumbnail.jpg\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": 19748,\n" +
                "                \"priority\": 0,\n" +
                "                \"signature\": \"32fae25d885686e634025854a5748e88\",\n" +
                "                \"instant\": \"2015-04-30 10:09:16\",\n" +
                "                \"created\": \"2016-05-05 16:57:45.099\",\n" +
                "                \"updated\": \"2016-05-05 17:08:20.580371\",\n" +
                "                \"caption\": \"Uploaded on 2016-05-05\",\n" +
                "                \"meta\": {\n" +
                "                    \"Source\": {\n" +
                "                        \"size\": 184029,\n" +
                "                        \"camera_make\": \"Canon\",\n" +
                "                        \"filename\": \"nepal-earthquake-surv_raym.jpg\",\n" +
                "                        \"camera_model\": \"Canon EOS 5D Mark III\",\n" +
                "                        \"height\": 817,\n" +
                "                        \"width\": 1200\n" +
                "                    }\n" +
                "                },\n" +
                "                \"version\": \"572b61e445c44ccc9f4b019a0a00020f\",\n" +
                "                \"location_latitude\": 27.706479301842,\n" +
                "                \"location_longitude\": 85.331668841884,\n" +
                "                \"location_time\": \"2016-05-05 17:08:20\",\n" +
                "                \"location_accuracy\": null,\n" +
                "                \"location_altitude\": null,\n" +
                "                \"location_provider\": \"manual\",\n" +
                "                \"mission_id\": null,\n" +
                "                \"mission_name\": null,\n" +
                "                \"thumbnail_hash\": \"35986ec3ef724e8b53514d101e9621c9\",\n" +
                "                \"preview_hash\": \"597753de42d0cabecf00556f9de37c48\",\n" +
                "                \"category_id\": null,\n" +
                "                \"category_name\": null,\n" +
                "                \"user_name\": \"Test Candidate\",\n" +
                "                \"user_id\": 166,\n" +
                "                \"user_username\": \"testcandidate\",\n" +
                "                \"user_color\": \"FFFFFF\",\n" +
                "                \"observation_url\": \"https://thor.ansur.no/signs/processing/19748\",\n" +
                "                \"preview_url\": \"https://thor.ansur.no/display/file/597753de42d0cabecf00556f9de37c48/testcandidate/32fae25d885686e634025854a5748e88/preview19788.webp.jpg\",\n" +
                "                \"thumbnail_url\": \"https://thor.ansur.no/display/file/35986ec3ef724e8b53514d101e9621c9/testcandidate/32fae25d885686e634025854a5748e88/thumbnail.jpg\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": 19746,\n" +
                "                \"priority\": 0,\n" +
                "                \"signature\": \"e630203b0c78351f649fbd166a4a491f\",\n" +
                "                \"instant\": \"2016-05-05 16:56:46\",\n" +
                "                \"created\": \"2016-05-05 16:56:44.352\",\n" +
                "                \"updated\": \"2016-05-05 17:07:23.218326\",\n" +
                "                \"caption\": \"Uploaded on 2016-05-05\",\n" +
                "                \"meta\": {\n" +
                "                    \"Source\": {\n" +
                "                        \"size\": 17046,\n" +
                "                        \"camera_make\": null,\n" +
                "                        \"filename\": \"irescue_forrest_fire_exercise_1.jpg\",\n" +
                "                        \"camera_model\": null,\n" +
                "                        \"height\": 268,\n" +
                "                        \"width\": 448\n" +
                "                    }\n" +
                "                },\n" +
                "                \"version\": \"572b61abc02c46f2af2304720a00020f\",\n" +
                "                \"location_latitude\": 39.37969116972,\n" +
                "                \"location_longitude\": -97.149696336578,\n" +
                "                \"location_time\": \"2016-05-05 17:07:23\",\n" +
                "                \"location_accuracy\": null,\n" +
                "                \"location_altitude\": null,\n" +
                "                \"location_provider\": \"manual\",\n" +
                "                \"mission_id\": null,\n" +
                "                \"mission_name\": null,\n" +
                "                \"thumbnail_hash\": \"089fa58bcdc848a2cf83a494c1fe8dc9\",\n" +
                "                \"preview_hash\": \"bb8ea63201734a6153c027f9479e4f15\",\n" +
                "                \"category_id\": null,\n" +
                "                \"category_name\": null,\n" +
                "                \"user_name\": \"Test Candidate\",\n" +
                "                \"user_id\": 166,\n" +
                "                \"user_username\": \"testcandidate\",\n" +
                "                \"user_color\": \"FFFFFF\",\n" +
                "                \"observation_url\": \"https://thor.ansur.no/signs/processing/19746\",\n" +
                "                \"preview_url\": \"https://thor.ansur.no/display/file/bb8ea63201734a6153c027f9479e4f15/testcandidate/e630203b0c78351f649fbd166a4a491f/preview29917.webp.jpg\",\n" +
                "                \"thumbnail_url\": \"https://thor.ansur.no/display/file/089fa58bcdc848a2cf83a494c1fe8dc9/testcandidate/e630203b0c78351f649fbd166a4a491f/thumbnail.jpg\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": 19745,\n" +
                "                \"priority\": 0,\n" +
                "                \"signature\": \"93e423998c3446384bf2c9abae6e948c\",\n" +
                "                \"instant\": \"2016-05-05 16:56:13\",\n" +
                "                \"created\": \"2016-05-05 16:56:11.427\",\n" +
                "                \"updated\": \"2016-05-05 17:06:34.257479\",\n" +
                "                \"caption\": \"Uploaded on 2016-05-05\",\n" +
                "                \"meta\": {\n" +
                "                    \"Source\": {\n" +
                "                        \"size\": 37094,\n" +
                "                        \"camera_make\": null,\n" +
                "                        \"filename\": \"Tecnologia-prevencao-catastrofes-via-satelite_ACRIMA20111005_0016_23.jpg\",\n" +
                "                        \"camera_model\": null,\n" +
                "                        \"height\": 314,\n" +
                "                        \"width\": 473\n" +
                "                    }\n" +
                "                },\n" +
                "                \"version\": \"572b617a65fc4a90bda302580a00020f\",\n" +
                "                \"location_latitude\": -20.365106836099,\n" +
                "                \"location_longitude\": -40.327677721132,\n" +
                "                \"location_time\": \"2016-05-05 17:06:34\",\n" +
                "                \"location_accuracy\": null,\n" +
                "                \"location_altitude\": null,\n" +
                "                \"location_provider\": \"manual\",\n" +
                "                \"mission_id\": null,\n" +
                "                \"mission_name\": null,\n" +
                "                \"thumbnail_hash\": \"22278bbf6e32b1f6db4dd3e7de952b9c\",\n" +
                "                \"preview_hash\": \"8dc30f1ed53a6b3f642f26a040de6bfa\",\n" +
                "                \"category_id\": null,\n" +
                "                \"category_name\": null,\n" +
                "                \"user_name\": \"Test Candidate\",\n" +
                "                \"user_id\": 166,\n" +
                "                \"user_username\": \"testcandidate\",\n" +
                "                \"user_color\": \"FFFFFF\",\n" +
                "                \"observation_url\": \"https://thor.ansur.no/signs/processing/19745\",\n" +
                "                \"preview_url\": \"https://thor.ansur.no/display/file/8dc30f1ed53a6b3f642f26a040de6bfa/testcandidate/93e423998c3446384bf2c9abae6e948c/preview893.webp.jpg\",\n" +
                "                \"thumbnail_url\": \"https://thor.ansur.no/display/file/22278bbf6e32b1f6db4dd3e7de952b9c/testcandidate/93e423998c3446384bf2c9abae6e948c/thumbnail.jpg\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": 19744,\n" +
                "                \"priority\": 0,\n" +
                "                \"signature\": \"7de66051fe0ac5771b92a7a53e685c7a\",\n" +
                "                \"instant\": \"2016-05-05 16:52:43\",\n" +
                "                \"created\": \"2016-05-05 16:52:40.76\",\n" +
                "                \"updated\": \"2016-05-05 17:05:23.791706\",\n" +
                "                \"caption\": \"Mayflyer! Uploaded on 2016-05-05\",\n" +
                "                \"meta\": {\n" +
                "                    \"Source\": {\n" +
                "                        \"size\": 101180,\n" +
                "                        \"camera_make\": null,\n" +
                "                        \"filename\": \"mayflyer.jpg\",\n" +
                "                        \"camera_model\": null,\n" +
                "                        \"height\": 1080,\n" +
                "                        \"width\": 1920\n" +
                "                    }\n" +
                "                },\n" +
                "                \"version\": \"572b6133fd1c47fea0aa7b760a00020f\",\n" +
                "                \"location_latitude\": 59.897483073049,\n" +
                "                \"location_longitude\": 10.614348648501,\n" +
                "                \"location_time\": \"2016-05-05 17:05:23\",\n" +
                "                \"location_accuracy\": null,\n" +
                "                \"location_altitude\": null,\n" +
                "                \"location_provider\": \"manual\",\n" +
                "                \"mission_id\": null,\n" +
                "                \"mission_name\": null,\n" +
                "                \"thumbnail_hash\": \"68270afcf2020ca85e9c7723beb69f36\",\n" +
                "                \"preview_hash\": \"a75f8c2e143c248a87860fde8fe16024\",\n" +
                "                \"category_id\": null,\n" +
                "                \"category_name\": null,\n" +
                "                \"user_name\": \"Test Candidate\",\n" +
                "                \"user_id\": 166,\n" +
                "                \"user_username\": \"testcandidate\",\n" +
                "                \"user_color\": \"FFFFFF\",\n" +
                "                \"observation_url\": \"https://thor.ansur.no/signs/processing/19744\",\n" +
                "                \"preview_url\": \"https://thor.ansur.no/display/file/a75f8c2e143c248a87860fde8fe16024/testcandidate/7de66051fe0ac5771b92a7a53e685c7a/preview96649.webp.jpg\",\n" +
                "                \"thumbnail_url\": \"https://thor.ansur.no/display/file/68270afcf2020ca85e9c7723beb69f36/testcandidate/7de66051fe0ac5771b92a7a53e685c7a/thumbnail.jpg\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": 19743,\n" +
                "                \"priority\": 0,\n" +
                "                \"signature\": \"0cd1920796b921f609c61404894c038c\",\n" +
                "                \"instant\": \"2012-07-20 05:41:46\",\n" +
                "                \"created\": \"2016-05-05 16:50:25.859\",\n" +
                "                \"updated\": \"2016-05-05 17:04:31.086834\",\n" +
                "                \"caption\": \"Uploaded on 2016-05-05\",\n" +
                "                \"meta\": {\n" +
                "                    \"Source\": {\n" +
                "                        \"size\": 4215601,\n" +
                "                        \"camera_make\": \"NIKON CORPORATION\",\n" +
                "                        \"filename\": \"drill1.jpg\",\n" +
                "                        \"camera_model\": \"NIKON D300\",\n" +
                "                        \"height\": 2136,\n" +
                "                        \"width\": 3216\n" +
                "                    }\n" +
                "                },\n" +
                "                \"version\": \"572b60ffb19c487bbb5f78d70a00020f\",\n" +
                "                \"location_latitude\": 40.689404267147,\n" +
                "                \"location_longitude\": -73.97627412243,\n" +
                "                \"location_time\": \"2016-05-05 17:04:31\",\n" +
                "                \"location_accuracy\": null,\n" +
                "                \"location_altitude\": null,\n" +
                "                \"location_provider\": \"manual\",\n" +
                "                \"mission_id\": null,\n" +
                "                \"mission_name\": null,\n" +
                "                \"thumbnail_hash\": \"f1934401bfdb25b8b7374073cf71dbdc\",\n" +
                "                \"preview_hash\": \"726cac98f01a3e4f2dada69d2ab3d0de\",\n" +
                "                \"category_id\": null,\n" +
                "                \"category_name\": null,\n" +
                "                \"user_name\": \"Test Candidate\",\n" +
                "                \"user_id\": 166,\n" +
                "                \"user_username\": \"testcandidate\",\n" +
                "                \"user_color\": \"FFFFFF\",\n" +
                "                \"observation_url\": \"https://thor.ansur.no/signs/processing/19743\",\n" +
                "                \"preview_url\": \"https://thor.ansur.no/display/file/726cac98f01a3e4f2dada69d2ab3d0de/testcandidate/0cd1920796b921f609c61404894c038c/preview56634.webp.jpg\",\n" +
                "                \"thumbnail_url\": \"https://thor.ansur.no/display/file/f1934401bfdb25b8b7374073cf71dbdc/testcandidate/0cd1920796b921f609c61404894c038c/thumbnail.jpg\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": 19742,\n" +
                "                \"priority\": 0,\n" +
                "                \"signature\": \"3c8bd912cb9b192de11f4553e11da8d7\",\n" +
                "                \"instant\": \"2016-05-05 16:49:36\",\n" +
                "                \"created\": \"2016-05-05 16:49:34.042\",\n" +
                "                \"updated\": \"2016-05-05 17:03:41.275688\",\n" +
                "                \"caption\": \"Uploaded on 2016-05-05\",\n" +
                "                \"meta\": {\n" +
                "                    \"Source\": {\n" +
                "                        \"size\": 146030,\n" +
                "                        \"camera_make\": null,\n" +
                "                        \"filename\": \"r0_0_1427_951_w1200_h678_fmax.jpg\",\n" +
                "                        \"camera_model\": null,\n" +
                "                        \"height\": 678,\n" +
                "                        \"width\": 1018\n" +
                "                    }\n" +
                "                },\n" +
                "                \"version\": \"572b60cdb8e84762a5e778d70a00020f\",\n" +
                "                \"location_latitude\": 51.199410748593,\n" +
                "                \"location_longitude\": -1.8973351914209,\n" +
                "                \"location_time\": \"2016-05-05 17:03:41\",\n" +
                "                \"location_accuracy\": null,\n" +
                "                \"location_altitude\": null,\n" +
                "                \"location_provider\": \"manual\",\n" +
                "                \"mission_id\": null,\n" +
                "                \"mission_name\": null,\n" +
                "                \"thumbnail_hash\": \"e6e1caaff5f83fb5546a2f071aea6fbc\",\n" +
                "                \"preview_hash\": \"35742d22734bde9bba79c781ea6bed19\",\n" +
                "                \"category_id\": null,\n" +
                "                \"category_name\": null,\n" +
                "                \"user_name\": \"Test Candidate\",\n" +
                "                \"user_id\": 166,\n" +
                "                \"user_username\": \"testcandidate\",\n" +
                "                \"user_color\": \"FFFFFF\",\n" +
                "                \"observation_url\": \"https://thor.ansur.no/signs/processing/19742\",\n" +
                "                \"preview_url\": \"https://thor.ansur.no/display/file/35742d22734bde9bba79c781ea6bed19/testcandidate/3c8bd912cb9b192de11f4553e11da8d7/preview37701.webp.jpg\",\n" +
                "                \"thumbnail_url\": \"https://thor.ansur.no/display/file/e6e1caaff5f83fb5546a2f071aea6fbc/testcandidate/3c8bd912cb9b192de11f4553e11da8d7/thumbnail.jpg\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": 19740,\n" +
                "                \"priority\": 0,\n" +
                "                \"signature\": \"164d0d30bd69c38c1561c340112a56c5\",\n" +
                "                \"instant\": \"2016-05-05 16:47:11\",\n" +
                "                \"created\": \"2016-05-05 16:47:09.218\",\n" +
                "                \"updated\": \"2016-05-05 17:02:08.191612\",\n" +
                "                \"caption\": \"Uploaded on 2016-05-05\",\n" +
                "                \"meta\": {\n" +
                "                    \"Source\": {\n" +
                "                        \"size\": 122122,\n" +
                "                        \"camera_make\": null,\n" +
                "                        \"filename\": \"Firefighter_web.jpg\",\n" +
                "                        \"camera_model\": null,\n" +
                "                        \"height\": 330,\n" +
                "                        \"width\": 730\n" +
                "                    }\n" +
                "                },\n" +
                "                \"version\": \"572b607001344ffe9acb02580a00020f\",\n" +
                "                \"location_latitude\": 34.913314405647,\n" +
                "                \"location_longitude\": -79.738694418298,\n" +
                "                \"location_time\": \"2016-05-05 17:02:08\",\n" +
                "                \"location_accuracy\": null,\n" +
                "                \"location_altitude\": null,\n" +
                "                \"location_provider\": \"manual\",\n" +
                "                \"mission_id\": null,\n" +
                "                \"mission_name\": null,\n" +
                "                \"thumbnail_hash\": \"ea2a476886c2557a84925eb832f74797\",\n" +
                "                \"preview_hash\": \"bee1f19ff4c1f8a3d2b1db273bf2c925\",\n" +
                "                \"category_id\": null,\n" +
                "                \"category_name\": null,\n" +
                "                \"user_name\": \"Test Candidate\",\n" +
                "                \"user_id\": 166,\n" +
                "                \"user_username\": \"testcandidate\",\n" +
                "                \"user_color\": \"FFFFFF\",\n" +
                "                \"observation_url\": \"https://thor.ansur.no/signs/processing/19740\",\n" +
                "                \"preview_url\": \"https://thor.ansur.no/display/file/bee1f19ff4c1f8a3d2b1db273bf2c925/testcandidate/164d0d30bd69c38c1561c340112a56c5/preview27981.webp.jpg\",\n" +
                "                \"thumbnail_url\": \"https://thor.ansur.no/display/file/ea2a476886c2557a84925eb832f74797/testcandidate/164d0d30bd69c38c1561c340112a56c5/thumbnail.jpg\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": 19749,\n" +
                "                \"priority\": 0,\n" +
                "                \"signature\": \"a5c5d832d114614bc23f6c76b5adb043\",\n" +
                "                \"instant\": \"2016-05-05 17:00:02\",\n" +
                "                \"created\": \"2016-05-05 17:00:01.016\",\n" +
                "                \"updated\": \"2016-05-05 17:00:02.182262\",\n" +
                "                \"caption\": \"Uploaded on 2016-05-05\",\n" +
                "                \"meta\": {\n" +
                "                    \"Source\": {\n" +
                "                        \"size\": 499405,\n" +
                "                        \"camera_make\": null,\n" +
                "                        \"filename\": \"4740974819_6f54bdf3fb_b.jpg\",\n" +
                "                        \"camera_model\": null,\n" +
                "                        \"height\": 768,\n" +
                "                        \"width\": 1024\n" +
                "                    }\n" +
                "                },\n" +
                "                \"version\": null,\n" +
                "                \"location_latitude\": null,\n" +
                "                \"location_longitude\": null,\n" +
                "                \"location_time\": null,\n" +
                "                \"location_accuracy\": null,\n" +
                "                \"location_altitude\": null,\n" +
                "                \"location_provider\": null,\n" +
                "                \"mission_id\": null,\n" +
                "                \"mission_name\": null,\n" +
                "                \"thumbnail_hash\": \"1dfd5310ab786cfabb192a544b0c4ecd\",\n" +
                "                \"preview_hash\": \"11dd0f72660d0b448d980fb296fbe8de\",\n" +
                "                \"category_id\": null,\n" +
                "                \"category_name\": null,\n" +
                "                \"user_name\": \"Test Candidate\",\n" +
                "                \"user_id\": 166,\n" +
                "                \"user_username\": \"testcandidate\",\n" +
                "                \"user_color\": \"FFFFFF\",\n" +
                "                \"observation_url\": \"https://thor.ansur.no/signs/processing/19749\",\n" +
                "                \"preview_url\": \"https://thor.ansur.no/display/file/11dd0f72660d0b448d980fb296fbe8de/testcandidate/a5c5d832d114614bc23f6c76b5adb043/preview36416.webp.jpg\",\n" +
                "                \"thumbnail_url\": \"https://thor.ansur.no/display/file/1dfd5310ab786cfabb192a544b0c4ecd/testcandidate/a5c5d832d114614bc23f6c76b5adb043/thumbnail.jpg\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": 19739,\n" +
                "                \"priority\": 0,\n" +
                "                \"signature\": \"23607b0616ae44536e3cefd9ebb33220\",\n" +
                "                \"instant\": \"2016-05-05 16:39:28\",\n" +
                "                \"created\": \"2016-05-05 16:39:26.264\",\n" +
                "                \"updated\": \"2016-05-05 16:44:24.051269\",\n" +
                "                \"caption\": \"Uploaded on 2016-05-05\",\n" +
                "                \"meta\": {\n" +
                "                    \"Source\": {\n" +
                "                        \"size\": 90149,\n" +
                "                        \"camera_make\": null,\n" +
                "                        \"filename\": \"Volunteer_firefighters_treat_a_car_wreck_victim.jpg\",\n" +
                "                        \"camera_model\": null,\n" +
                "                        \"height\": 600,\n" +
                "                        \"width\": 800\n" +
                "                    }\n" +
                "                },\n" +
                "                \"version\": \"572b5b9f6cac4c1a8b6975ec0a00020f\",\n" +
                "                \"location_latitude\": 45.950276918783,\n" +
                "                \"location_longitude\": 2.6377165313864,\n" +
                "                \"location_time\": \"2016-05-05 16:41:35\",\n" +
                "                \"location_accuracy\": null,\n" +
                "                \"location_altitude\": null,\n" +
                "                \"location_provider\": \"manual\",\n" +
                "                \"mission_id\": null,\n" +
                "                \"mission_name\": null,\n" +
                "                \"thumbnail_hash\": \"01d195dcfa631f0a1149f040ef327dba\",\n" +
                "                \"preview_hash\": \"a5ec67a24bf267508c6b480faf3f1983\",\n" +
                "                \"category_id\": null,\n" +
                "                \"category_name\": null,\n" +
                "                \"user_name\": \"Test Candidate\",\n" +
                "                \"user_id\": 166,\n" +
                "                \"user_username\": \"testcandidate\",\n" +
                "                \"user_color\": \"FFFFFF\",\n" +
                "                \"observation_url\": \"https://thor.ansur.no/signs/processing/19739\",\n" +
                "                \"preview_url\": \"https://thor.ansur.no/display/file/a5ec67a24bf267508c6b480faf3f1983/testcandidate/23607b0616ae44536e3cefd9ebb33220/preview49376.webp.jpg\",\n" +
                "                \"thumbnail_url\": \"https://thor.ansur.no/display/file/01d195dcfa631f0a1149f040ef327dba/testcandidate/23607b0616ae44536e3cefd9ebb33220/thumbnail.jpg\"\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    \"meta\": {\n" +
                "        \"status\": \"ok\",\n" +
                "        \"feedback\": [],\n" +
                "        \"request\": {\n" +
                "            \"http_host\": \"thor.ansur.no\",\n" +
                "            \"server_addr\": \"172.18.0.4\",\n" +
                "            \"remote_addr\": \"172.18.0.3\",\n" +
                "            \"server_protocol\": \"HTTP/1.1\",\n" +
                "            \"request_method\": \"GET\",\n" +
                "            \"request_uri\": \"/api/photos.json\",\n" +
                "            \"request_time\": 1562845714\n" +
                "        },\n" +
                "        \"credentials\": {\n" +
                "            \"class\": null,\n" +
                "            \"apikey\": null,\n" +
                "            \"username\": \"testcandidate\"\n" +
                "        },\n" +
                "        \"time_epoch\": 1562845714,\n" +
                "        \"time_local\": \"Thu, 11 Jul 2019 11:48:34 +0000\",\n" +
                "        \"version\": 0.3\n" +
                "    }\n" +
                "}");
        //conn.setRequestProperty("content-type", "application/json");
        //conn.setRequestProperty("charset","UTF-8");

        //A workaround to go ahead I couldn't make it work with the line below
        //conn.setRequestProperty("Authorization", "Basic " + Base64.encode(AUTH.getBytes(), Base64.NO_WRAP));
        //conn.setRequestProperty("Authorization", " Basic " + "dGVzdGNhbmRpZGF0ZTpieWdjZDMz");

        //Checking response code
        //rCode = conn.getResponseCode();
        switch (rCode){
            case 200:
                Log.d("Connection","SUCCEDED");
                break;
            case 400:
                Log.d("Connection","Malformed parameter");
            case 401:
                Log.d("Connection","Auth error: Missing/wrong auth");
                break;
            case 403:
                Log.d("Connection","Auth error: User not auth");
                break;
            case 404:
                Log.d("Connection","Resource not found");
                break;
            case 409:
                Log.d("Connection","Some kind of conflict");
                break;
        }
    }
    //Closing the connection
    public static void closeConn() throws IOException {
        //responseBodyReader.close();
        //responseBody.close();
        //   conn.disconnect();
    }

    //Get data from API connection
    public static ArrayList<Thumbnail> getData() throws IOException{
        //responseBody = conn.getInputStream();
        //responseBodyReader = new InputStreamReader(responseBody, "UTF-8");
            //jsonData = readJson();
        //closeConn();

        return jsonData;
    }
    //Iterate json file til find thumbnail_url key and value
    public static ArrayList<Thumbnail> readJson() throws IOException {
        ArrayList<Thumbnail> data = new ArrayList<>();
        int count = 0;

        JsonReader jsonReader = new JsonReader(new StringReader(conn));

        jsonReader.beginObject(); //We're inside "Root Object"
        while(jsonReader.hasNext()){
            final String name = jsonReader.nextName();
            final boolean isNull = jsonReader.peek() == JsonToken.NULL; //Check if the key is null
            if (name.equals("data") && !isNull){
                jsonReader.beginObject();   //We're inside "Data Object// "
                while(jsonReader.hasNext() && !isNull){
                    final String innerName = jsonReader.nextName();
                    final boolean isInnerNull = jsonReader.peek() == JsonToken.NULL;
                    if (innerName.equals("photos")){
                        jsonReader.beginArray(); //We're inside "Photos array"
                        while(jsonReader.hasNext() && !isInnerNull){
                            jsonReader.beginObject(); // We're inside each array element
                            final boolean isInner3Null = jsonReader.peek() == JsonToken.NULL;
                            Thumbnail myThumb = new Thumbnail(); //Get ready to create the thumbnails
                            while(jsonReader.hasNext() && !isInner3Null){
                                final String inner2Name = jsonReader.nextName();
                                final boolean isValNull = jsonReader.peek() == JsonToken.NULL; //Check if the coordinates are null
                                if(inner2Name.equals("id")) {//Get the id
                                    myThumb.setId(jsonReader.nextInt());
                                }
                                else if(inner2Name.equals("location_latitude")&& !isValNull){ //Get the latitude
                                    try {
                                        myThumb.setLatitude(jsonReader.nextDouble());
                                    }
                                    catch(NumberFormatException e){
                                        e.printStackTrace();
                                    }
                                }
                                else if(inner2Name.equals("location_longitude")&& !isValNull){ //Get the longitude
                                    try {
                                        myThumb.setLongitude(jsonReader.nextDouble());
                                    }
                                    catch(NumberFormatException e){
                                        e.printStackTrace();
                                    }
                                }
                                else if(inner2Name.equals("thumbnail_url")){ //Get the thumbnail_url
                                    myThumb.setUrl(jsonReader.nextString());
                                }
                                else
                                    jsonReader.skipValue();
                            }
                            data.add(myThumb); //Add the object
                            jsonReader.endObject();
                        }
                        jsonReader.endArray();
                    }
                    else
                        jsonReader.skipValue(); //If null or key doesn't match
                }
                jsonReader.endObject();
            }
            else
                jsonReader.skipValue();
        }
        jsonReader.endObject();

        //mReader.close();
        jsonData = data;
        return data;
    }

    public static int getJsonSize(){ return jsonData.size(); }
    public static Thumbnail getThumbnail(int pos){ return jsonData.get(pos);}
}
