package com.example.colors2web.clientportal.api;

import com.example.colors2web.clientportal.POJO.Inventories.AllItems.CustomerItems.ResponseCustomerItems;
import com.example.colors2web.clientportal.POJO.Inventories.AllItems.Customers.PutCustomer;
import com.example.colors2web.clientportal.POJO.Inventories.AllItems.Customers.ResponseCustomer;
import com.example.colors2web.clientportal.POJO.Inventories.AllItems.UOM.ResponseUOM;
import com.example.colors2web.clientportal.POJO.Inventories.InActiveItems.ResponseInActive;
import com.example.colors2web.clientportal.POJO.InventoriesDeleveries.Deleveries.ResponseDelivery;
import com.example.colors2web.clientportal.POJO.CustomerDetails.ResponseCustomers;
import com.example.colors2web.clientportal.POJO.InventoriesDeleveries.DeliveryHistory.ResponseDrHistory;
import com.example.colors2web.clientportal.POJO.InventoriesDeleveries.PostItem;
import com.example.colors2web.clientportal.POJO.Offices.ResponseOffice;
import com.example.colors2web.clientportal.POJO.login.Login;
import com.example.colors2web.clientportal.POJO.login.ResponseLogin;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface APIInterface {

    //Login Authentication
    @POST("auth/login")
    Call<ResponseLogin> dologin(@Body Login login);

//    // GET USERS
////    https://zumsapi.com/users/getUsersByCustomerId/43
//    @GET("users/getUsersByCustomerId/{customer_id}")
//    Call<ResponseUsers> getUser(@Header("email") String email, @Header("Password") String password, @Path("customer_id") String customer_id);
//
//    //POST USERS
//    @POST("users")
//    Call<ResponseUsers> createUsers(@Header("email") String email, @Header("Password") String password, @Body UserCreatePOJO pojo);
//
//    //update users
//    @PUT("users/{userId}")
//    Call<ResponseUsers> updateusers(@Header("email") String email, @Header("Password") String password, @Path("userId") String userId, @Body UserCreatePOJO pojo);

//    customerDetails//    Edit Customer
    @PUT("/customers/updateReturnAddress/{customer_id}")
    Call<ResponseCustomer>editCustomerDetails(@Header("email") String email, @Header("password")String password, @Path("customer_id")String customer_id, @Body PutCustomer putcustomer);


    //    Inventories //InActive
    @GET("customerItems/inactiveItems/customerId/{customer_id}")
    Call<ResponseInActive> getInActiveItems1(@Header("email") String email, @Header("Password") String password, @Path("customer_id") String customer_id);

    //    Inventories //InActive with special programs
    @GET("customerItems/inactiveItems/customerId/{customer_id}/specialProgram/{specialProgram/}")
    Call<ResponseInActive> getInActiveItems2(@Header("email") String email, @Header("Password") String password, @Path("customer_id") String customer_id, @Path("specialProgram") String specialProgram);

    //      Inventories //  ALL Items //  Customers
    @GET("customers/{customer_id}")
    Call<ResponseCustomer> getCustomer(@Header("email") String email, @Header("Password") String password, @Path("customer_id") String customer_id);

    //      Inventories //  ALL Items //  CustomerItems
    @GET("customerItems/customer/{customer_id}")
    Call<ResponseCustomerItems> getCustomerItems1(@Header("email") String email, @Header("Password") String password, @Path("customer_id") String customer_id);

    //    Inventories //InActive with special programs
    @GET("/customerItems/customer/{customer_id}/specialProgram/{specialProgram/}")
    Call<ResponseCustomerItems> getCustomerItems2(@Header("email") String email, @Header("Password") String password, @Path("customer_id") String customer_id, @Path("specialProgram") String specialProgram);

//    Get Unit of Measurement
    @GET("unitOfMeasurements")
    Call<ResponseUOM> getUOM(@Header("email") String email, @Header("Password") String password);

//    Deleveries or Dr shipment
    @GET("drShipments/serviceStatus/all")
    Call<ResponseDelivery> getDeleveries(@Header("email") String email, @Header("password") String password, @Query("customer_id") String customer_id);


    //    // offices//https://zumsapi.com//cityBins/boxCreate/customer/43
    @GET("cityBins/boxCreate/customer/{customer_id}")
    Call<ResponseOffice> getCityBins(@Header("email") String email, @Header("Password") String password, @Path("customer_id") String customer_id);

//    drShipments/searchItemDrHistory
    @POST("drShipments/searchItemDrHistory")
    Call<ResponseDrHistory> postItemDrHistory(@Header("email") String email, @Header("Password") String password, @Body PostItem itemdr);


//
//    @PUT("orders/markExpedite/{order_id}")
//    Call<OrderEditResponse> putmarkExpedite(@Header("email") String email, @Header("Password") String password, @Path("order_id") String order_number, @Body EditExpedite editExpedite);
//


//    // tracking number merge on dropdown search api
//    @GET("orders/searchOrderByTrackingNumber/{order_number}")
//    Call<Order2Response> getOrderByTrackingNumber(@Header("email") String email, @Header("Password") String password, @Path("order_number") String order_number);
//
//    // box or masterbox search api =>
//    @GET("boxes/masterBoxes/{order_number}")
//    Call<MasterBoxResponse> getMasterBoxes(@Header("email") String email, @Header("Password") String password, @Path("order_number") String order_number);
//
//    // customers search by customer_id =>
//    @GET("customers/{customer_id}")
//    Call<CustomerResponse> getCustomer(@Header("email") String email, @Header("Password") String password, @Path("customer_id") String customer_id);
//
//    // customers search by parent Customer Id =>Hardcoded for now
//    //    @GET("customers/parentCustomer/{parent_id}")
//    @GET("customers/parentCustomer/0")
//    Call<CustomerResponse> getParentCustomer(@Header("email") String email, @Header("Password") String password);
//
//    // Activecustomers search  =>
//    @GET("customers/activeCustomers")
//    Call<CustomerResponse> getActiveCustomers(@Header("email") String email, @Header("Password") String password);
//
//    // customers Itemssearch by customer_id =>
//    @GET("customerItems/customer/{customer_id}")
//    Call<CustomerResponse> getCustomerItems(@Header("email") String email, @Header("Password") String password, @Path("customer_id") String customer_id);
//
//
//    // report/getPickVelocityAndInventory
//    @POST("report/getPickVelocityAndInventory")
//    Call<PostResponse> postPickVelocityReport(@Header("email") String email, @Header("Password") String password, @Body PostServer itemdr);
//
//
//    //orders/searchItemSalesHistory
//    @POST("orders/searchItemSalesHistory")
//    Call<PostResponse> postItemSalesHistory(@Header("email") String email, @Header("Password") String password, @Body PostServer itemdr);
//
//    //    CityBins API
//    //    Get all customers
//    @GET("cityBins/groupedByCustomer/all")
//    Call<CityBinsResponse> getBinCustomers(@Header("email") String email, @Header("Password") String password);
//
//    //    Retriving bins of individual customer
//    //    cityBins/boxCreate/customer/3
//    @GET("cityBins/boxCreate/customer/{customer_id}")
//    Call<CityBinsResponse> getBins(@Header("email") String email, @Header("Password") String password, @Path("customer_id") String cus_id);
//
//
//    //  get citybins with bin ind only in case of update
//    @GET("cityBins/{id}")
//    Call<CityEditResponse> getCitybin(@Header("email") String email, @Header("Password") String password, @Path("id") String id);
//
//
//    //    update city bin api
//    @PUT("cityBins/{id}")
//    Call<CityBinsResponse> putCitybin(@Header("email") String email, @Header("Password") String password, @Path("id") String id, @Body CityBins bins);
//
//    //    Create City Bins Either from Inside or Outside
//    @POST("cityBins")
//    Call<CityBinsResponse> CreateCitybin(@Header("email") String email, @Header("Password") String password, @Body CityBins bins);
//
//    //    ProblenSKU
//    @GET("problemSku")
//    Call<ProblemResponse> getProblemSKU(@Header("email") String email, @Header("Password") String password);
//
//    //    getUOM
//    @GET("unitOfMeasurements")
//    Call<ProblemResponse> getUOM(@Header("email") String email, @Header("Password") String password);
//
//    @POST("unitOfMeasurements")
//    Call<ProblemResponse> postUOM(@Header("email") String email, @Header("Password") String password, @Body UOM uom);
//
//
//    //    post for problems
//    //    /customerItems/edit/customer/'.$input['customer_id'].'/sku/'.$input['item_sku_number']
//    @POST("customerItems/edit/customer/{customerID}/sku/{ItemSku}")
//    Call<ProblemResponse> postProblem(@Header("email") String email, @Header("Password") String password, @Path("customerID") String cus_id, @Path("ItemSku") String item_sku, @Body ProblemInput input);
//
//    //    Delete for problemsku
//    //problemSku/'.$input['id'],
//    @DELETE("problemSku/{id}")
//    Call<ProblemResponse> deleteProblemsku(@Header("email") String email, @Header("Password") String password, @Path("id") String id);
//
//    //    Update for problemsku
//    @PUT("problemSku/{id}")
//    Call<ProblemResponse> updateProblemsku(@Header("email") String email, @Header("Password") String password, @Path("id") String id, @Body ProblemInput input);
//
//    //    Packages
//    @GET("packages/customer/0")
//    Call<ProblemResponse> getPackages(@Header("email") String email, @Header("Password") String password);
//
//    //    post for packages
//    @POST("packages")
//    Call<ProblemResponse> postPackage(@Header("email") String email, @Header("Password") String password, @Body PackageInput input);
//
//    //  update for packages
//    @PUT("packages/{packageId}")
//    Call<ProblemResponse> updatePackage(@Header("email") String email, @Header("Password") String password, @Path("packageId") String packageId, @Body PackageInput input);
//
//    //    Box inside MasterBox
//    //boxes/masterBoxes/2465840302/getAllBoxes
//    @GET("boxes/masterBoxes/{masterBoxNumber}/getAllBoxes")
//    Call<MasterBoxResponse> getMboxBox(@Header("email") String email, @Header("Password") String password, @Path("masterBoxNumber") String mBox);
//
//
//    //  Items inside  Box inside MasterBox
//    // boxes/lineItems/boxNumber/2047023661
//    @GET("boxes/lineItems/boxNumber/{boxNumber}")
//    Call<MasterBoxResponse> getMboxBoxItems(@Header("email") String email, @Header("Password") String password, @Path("boxNumber") String box);
//
//    //    https://f860f607.ngrok.io/zummix-api/public/orderBatch/allBatchOrders/batchNumber/153596655211
//    //  BatchSearch
//    @GET("orderBatch/allBatchOrders/batchNumber/{batchnumber}")
//    Call<BatchResponse> getBatchResponse(@Header("email") String email, @Header("Password") String password, @Path("batchnumber") String batchnumber);
//
//    //    InactiveItems Search //https://f860f607.ngrok.io/zummix-api/public/customerItems/inactiveItems/customerId/15
//    @GET("customerItems/inactiveItems/customerId/{customerid}")
//    Call<InactiveResponse> getInactiveFilteredResponse(@Header("email") String email, @Header("Password") String password, @Path("customerid") String customerid);
//
//    // ProductDetails//Address
//    // https://40ecef9e.ngrok.io/zummix-api/public/customerItems/searchedCustomerItemsDetail/1334
//    @GET("customerItems/searchedCustomerItemsDetail/{itemid}")
//    Call<ProductDetailsResponse> getProductitems(@Header("email") String email, @Header("Password") String password, @Path("itemid") String itemid);
//
//
//    //  drShipmentItemLocations/sku/TEST150001/customer/15
//    @GET("drShipmentItemLocations/sku/{sku_id}/customer/{customer_id}")
//    Call<ProductDetailsResponse> getProductLocations(@Header("email") String email, @Header("Password") String password, @Path("sku_id") String sku_id, @Path("customer_id") String customer_id);
//
//
//    // ProductDetails//Logs
//    //inventoryLogs/customerId/15/sku/TEST150001/eventType/all
//    @GET("inventoryLogs/customerId/{customer_id}/sku/{sku_id}/eventType/all")
//    Call<ProductDetailsResponse> getProductLogs(@Header("email") String email, @Header("Password") String password, @Path("customer_id") String customer_id, @Path("sku_id") String sku_id);
//

//
//    //GET GROUPS
//    @GET("groups")
//    Call<GroupResponse> getGroups(@Header("email") String email, @Header("Password") String password);
//
//
//    @GET("specialPrograms/getSpecialProgramsByCustomerId/customer/{customer_id}")
//    Call<SProgramResponse> getSpecialPrograms(@Header("email") String email, @Header("Password") String password, @Path("customer_id") String customer_id);
//
//    //ALl customers with specialprograms
//    @GET("specialPrograms/getCustomersWithSpecialProgramsCount/getAllCustomers")
//    Call<SpecialProgramResponse> getSpecialPCustomers(@Header("email") String email, @Header("Password") String password);
//
//    //creating Special Response
//    @POST("specialPrograms")
//    Call<SpecialProgramResponse> createSpecialPrograms(@Header("email") String email, @Header("Password") String password, @Body SpecialProgram program);
//
//    //updating Special Response
//    @PUT("specialPrograms/{specialProgramId}")
//    Call<SpecialProgramResponse> updateSpecialPrograms(@Header("email") String email, @Header("Password") String password, @Path("specialProgramId") String specialProgramId, @Body SpecialProgram program);
//
//    //    Retreiving cron_job Reports
////    /cron_jobs/batchAndOrderDetailsV2/from/2017-09-01/to/2018-09-21
//    @GET("cron_jobs/batchAndOrderDetailsV2/from/{from}/to/{to}")
//    Call<CronJobResponse> getCronJobs(@Header("email") String email, @Header("Password") String password,
//                                      @Path("from") String from, @Path("to") String to);
//
//    //    http://732b4c9a.ngrok.io/zummix-api/public/orders/status/totalOrders/customer/all/orderType/1/from/2018-09-01/to/2018-09-21
////    Details on clicking cronjobs fields
//    @GET("orders/status/{orderstatus}/customer/{customer_id}/orderType/{ordertype}/from/{from}/to/{to}")
//    Call<CronDetailsResponse> getCronJobsDetails(@Header("email") String email, @Header("Password") String password,
//                                                 @Path("orderstatus") String orderstatus, @Path("customer_id") String customer_id, @Path("ordertype") String ordertype,
//                                                 @Path("from") String from, @Path("to") String to);
//
//
//    //    FOr cron job's company name //2
//    @GET("customers/{customer_id}")
//    Call<CompanyResponse> getCOmpanyName(@Header("email") String email, @Header("password") String password);
//
//    //    FOr supportportal Tickets
//    @GET("tickets")
//    Call<TicketResponse> getTickets(@Header("email") String email, @Header("password") String password);
//
//    //    For Return Tickets
//    @GET("returnTickets")
//    Call<ReturnTicketResponse> getReturnTickets(@Header("email") String email, @Header("password") String password);
//
//    //    GET Ticket Support Details
//    @GET("stores/{id}/supports/{supportid}")
//    Call<TicketDetialsResponse> getTicketDetails(@Header("email") String email, @Header("password") String password,
//                                                 @Path("id") String id, @Path("supportid") String supportid);
//
//    //    GET Ticket Order Details
//    @GET("stores/{id}/supports/{supportid}/order/{order_id}")
//    Call<TicketOrderDetailsResponse> getTicketOrderDetails(@Header("email") String email, @Header("password") String password,
//                                                           @Path("id") String id, @Path("supportid") String supportid,
//                                                           @Path("order_id") String order_id );
//
//    //    For Return Tickets
//    @GET("search_tickets/{ticket_number}")
//    Call<TicketResponse> getSearchTickets(@Header("email") String email, @Header("password") String password, @Path("ticket_number") String ticket_number);
//
////    For Comment reply
//    @PUT("stores/{support_id}")
//    Call<ReplyResponse> putCommentReply(@Header("email") String email, @Header("password") String password, @Path("support_id") String support_id, @Body CommentInput input);
//
////    For Mark CLose  stores/status/2
//   @PUT("stores/status/{support_id}")
//   Call<ReplyResponse> putMarkClose(@Header("email") String email, @Header("password") String password, @Path("support_id") String support_id, @Body MarkInput input);
//
//    @GET("stores/reopen1/{support_id}")
//    Call<ReplyResponse> getReopen(@Header("email") String email, @Header("password") String password, @Path("support_id") String support_id);

}

