package com.imbank.ussdbackend.external.requests;

import liquibase.pro.packaged.T;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

@Service
public class BalanceInquiry {

    public String createBalanceInquiryRequest(String accountNumber){
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<FIXML xsi:schemaLocation=\"http://www.finacle.com/fixml BalInq.xsd\" xmlns=\"http://www.finacle.com/fixml\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><Header>\n" +
                "<RequestHeader>\n" +
                "<MessageKey>\n" +
                "<RequestUUID>Req_1557320090182</RequestUUID>\n" +
                "<ServiceRequestId>BalInq</ServiceRequestId>\n" +
                "<ServiceRequestVersion>10.2</ServiceRequestVersion>\n" +
                "<ChannelId>COR</ChannelId>\n" +
                "<LanguageId></LanguageId>\n" +
                "</MessageKey>\n" +
                "<RequestMessageInfo>\n" +
                "<BankId></BankId>\n" +
                "<TimeZone></TimeZone>\n" +
                "<EntityId></EntityId>\n" +
                "<EntityType></EntityType>\n" +
                "<ArmCorrelationId></ArmCorrelationId>\n" +
                "<MessageDateTime>2019-04-08T15:54:50.182</MessageDateTime>\n" +
                "</RequestMessageInfo>\n" +
                "<Security>\n" +
                "<Token>\n" +
                "<PasswordToken>\n" +
                "<UserId></UserId>\n" +
                "<Password></Password>\n" +
                "</PasswordToken>\n" +
                "</Token>\n" +
                "<FICertToken></FICertToken>\n" +
                "<RealUserLoginSessionId></RealUserLoginSessionId>\n" +
                "<RealUser></RealUser>\n" +
                "<RealUserPwd></RealUserPwd>\n" +
                "<SSOTransferToken></SSOTransferToken>\n" +
                "</Security>\n" +
                "</RequestHeader>\n" +
                "</Header>\n" +
                "<Body>\n" +
                "<BalInqRequest>\n" +
                "<BalInqRq>\n" +
                "<AcctId>\n" +
                "<!-- Account Number to be Passed in Below Field -->\n" +
                "<AcctId>" + accountNumber + "</AcctId>\n" +
                "</AcctId>\n" +
                "</BalInqRq>\n" +
                "</BalInqRequest>";
    }
    public void postTest(){
        HttpHeaders headers = createHeaders("imbankkeuat","imbank@2020");
        String requestBody = createBalanceInquiryRequest("00100679252910");
        ResponseEntity<String> response = new RestTemplate().exchange(url, HttpMethod.GET, requestBody, String.class);

        try {
            // request url
            String url = "https://192.168.205.26:1783/restgateway/services/KEFIUATService/fihttp";
            // create auth credentials
            String authStr = "username:password";
            String base64Creds = Base64.getEncoder().encodeToString(authStr.getBytes());
            // create headers
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Basic " + base64Creds);
            // create request
            HttpEntity request = new HttpEntity(headers);
            // make a request
            ResponseEntity<String> response = new RestTemplate().exchange(url, HttpMethod.GET, request, String.class);
            // get JSON response
            String json = response.getBody();
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }

    public HttpHeaders createHeaders(String username, String password){
        return new HttpHeaders() {{
            String auth = username + ":" + password;
            byte[] encodedAuth = Base64.encodeBase64(
                    auth.getBytes(Charset.forName("US-ASCII")) );
            String authHeader = "Basic " + new String( encodedAuth );
            set( "Authorization", authHeader );
        }};
    }
//    String resp = resttemplate.postFO....(request, String.class);

//    public String balanceInquiryResponse() {
//    return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"+ "<FIXML xsi:schemaLocation=\"http://www.finacle.com/fixml BalInq.xsd\" xmlns=\"http://www.finacle.com/fixml\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
//            "   <Header>\n" +
//            "      <ResponseHeader>\n" +
//            "         <RequestMessageKey>\n" +
//            "            <RequestUUID>Req_1557320090182</RequestUUID>\n" +
//            "            <ServiceRequestId>BalInq</ServiceRequestId>\n" +
//            "            <ServiceRequestVersion>10.2</ServiceRequestVersion>\n" +
//            "            <ChannelId>COR</ChannelId>\n" +
//            "         </RequestMessageKey>\n" +
//            "         <ResponseMessageInfo>\n" +
//            "            <BankId>01</BankId>\n" +
//            "            <TimeZone>GMT+05:30</TimeZone>\n" +
//            "            <MessageDateTime>2020-02-17T14:18:17.421</MessageDateTime>\n" +
//            "         </ResponseMessageInfo>\n" +
//            "         <UBUSTransaction>\n" +
//            "            <Id/>\n" +
//            "            <Status/>\n" +
//            "         </UBUSTransaction>\n" +
//            "         <HostTransaction>\n" +
//            "            <Id/>\n" +
//            "            <Status>SUCCESS</Status>\n" +
//            "         </HostTransaction>\n" +
//            "         <HostParentTransaction>\n" +
//            "            <Id/>\n" +
//            "            <Status/>\n" +
//            "         </HostParentTransaction>\n" +
//            "         <CustomInfo/>\n" +
//            "      </ResponseHeader>\n" +
//            "   </Header>\n" +
//            "   <Body>\n" +
//            "      <BalInqResponse>\n" +
//            "         <BalInqRs>\n" +
//            "            <AcctId>\n" +
//            "               <AcctId>00100679252910</AcctId>\n" +
//            "               <AcctType>\n" +
//            "                  <SchmCode/>\n" +
//            "                  <SchmType/>\n" +
//            "               </AcctType>\n" +
//            "               <AcctCurr>KES</AcctCurr>\n" +
//            "               <BankInfo>\n" +
//            "                  <BankId/>\n" +
//            "                  <Name/>\n" +
//            "                  <BranchId>001</BranchId>\n" +
//            "                  <BranchName/>\n" +
//            "                  <PostAddr>\n" +
//            "                     <Addr1/>\n" +
//            "                     <Addr2/>\n" +
//            "                     <Addr3/>\n" +
//            "                     <City/>\n" +
//            "                     <StateProv/>\n" +
//            "                     <PostalCode/>\n" +
//            "                     <Country/>\n" +
//            "                     <AddrType/>\n" +
//            "                  </PostAddr>\n" +
//            "               </BankInfo>\n" +
//            "            </AcctId>\n" +
//            "            <AcctBal>\n" +
//            "               <BalType>CLRBAL</BalType>\n" +
//            "               <BalAmt>\n" +
//            "                  <amountValue>8058568.30</amountValue>\n" +
//            "                  <currencyCode>KES</currencyCode>\n" +
//            "               </BalAmt>\n" +
//            "            </AcctBal>\n" +
//            "            <AcctBal>\n" +
//            "               <BalType>SANLIM</BalType>\n" +
//            "               <BalAmt>\n" +
//            "                  <amountValue>0.00</amountValue>\n" +
//            "                  <currencyCode>KES</currencyCode>\n" +
//            "               </BalAmt>\n" +
//            "            </AcctBal>\n" +
//            "            <AcctBal>\n" +
//            "               <BalType>UTLAMT</BalType>\n" +
//            "               <BalAmt>\n" +
//            "                  <amountValue>0.00</amountValue>\n" +
//            "                  <currencyCode>KES</currencyCode>\n" +
//            "               </BalAmt>\n" +
//            "            </AcctBal>\n" +
//            "            <AcctBal>\n" +
//            "               <BalType>DRWPWR</BalType>\n" +
//            "               <BalAmt>\n" +
//            "                  <amountValue>0.00</amountValue>\n" +
//            "                  <currencyCode>KES</currencyCode>\n" +
//            "               </BalAmt>\n" +
//            "            </AcctBal>\n" +
//            "            <AcctBal>\n" +
//            "               <BalType>ADHOC</BalType>\n" +
//            "               <BalAmt>\n" +
//            "                  <amountValue>0.00</amountValue>\n" +
//            "                  <currencyCode>KES</currencyCode>\n" +
//            "               </BalAmt>\n" +
//            "            </AcctBal>\n" +
//            "            <AcctBal>\n" +
//            "               <BalType>SINTRN</BalType>\n" +
//            "               <BalAmt>\n" +
//            "                  <amountValue>0.00</amountValue>\n" +
//            "                  <currencyCode>KES</currencyCode>\n" +
//            "               </BalAmt>\n" +
//            "            </AcctBal>\n" +
//            "            <AcctBal>\n" +
//            "               <BalType>CLNADH</BalType>\n" +
//            "               <BalAmt>\n" +
//            "                  <amountValue>0.00</amountValue>\n" +
//            "                  <currencyCode>KES</currencyCode>\n" +
//            "               </BalAmt>\n" +
//            "            </AcctBal>\n" +
//            "            <AcctBal>\n" +
//            "               <BalType>DACC</BalType>\n" +
//            "               <BalAmt>\n" +
//            "                  <amountValue>0.00</amountValue>\n" +
//            "                  <currencyCode>KES</currencyCode>\n" +
//            "               </BalAmt>\n" +
//            "            </AcctBal>\n" +
//            "            <AcctBal>\n" +
//            "               <BalType>LIEN</BalType>\n" +
//            "               <BalAmt>\n" +
//            "                  <amountValue>0.00</amountValue>\n" +
//            "                  <currencyCode>KES</currencyCode>\n" +
//            "               </BalAmt>\n" +
//            "            </AcctBal>\n" +
//            "            <AcctBal>\n" +
//            "               <BalType>SYSRES</BalType>\n" +
//            "               <BalAmt>\n" +
//            "                  <amountValue>0.00</amountValue>\n" +
//            "                  <currencyCode>KES</currencyCode>\n" +
//            "               </BalAmt>\n" +
//            "            </AcctBal>\n" +
//            "            <AcctBal>\n" +
//            "               <BalType>OVDFUT</BalType>\n" +
//            "               <BalAmt>\n" +
//            "                  <amountValue>0.00</amountValue>\n" +
//            "                  <currencyCode>KES</currencyCode>\n" +
//            "               </BalAmt>\n" +
//            "            </AcctBal>\n" +
//            "            <AcctBal>\n" +
//            "               <BalType>DAFALM</BalType>\n" +
//            "               <BalAmt>\n" +
//            "                  <amountValue>0.00</amountValue>\n" +
//            "                  <currencyCode>KES</currencyCode>\n" +
//            "               </BalAmt>\n" +
//            "            </AcctBal>\n" +
//            "            <AcctBal>\n" +
//            "               <BalType>FUTBAL</BalType>\n" +
//            "               <BalAmt>\n" +
//            "                  <amountValue>14942.00</amountValue>\n" +
//            "                  <currencyCode>KES</currencyCode>\n" +
//            "               </BalAmt>\n" +
//            "            </AcctBal>\n" +
//            "            <AcctBal>\n" +
//            "               <BalType>FLOAT</BalType>\n" +
//            "               <BalAmt>\n" +
//            "                  <amountValue>0.00</amountValue>\n" +
//            "                  <currencyCode>KES</currencyCode>\n" +
//            "               </BalAmt>\n" +
//            "            </AcctBal>\n" +
//            "            <AcctBal>\n" +
//            "               <BalType>AVAIL</BalType>\n" +
//            "               <BalAmt>\n" +
//            "                  <amountValue>8054854.30</amountValue>\n" +
//            "                  <currencyCode>KES</currencyCode>\n" +
//            "               </BalAmt>\n" +
//            "            </AcctBal>\n" +
//            "            <AcctBal>\n" +
//            "               <BalType>FFDAVL</BalType>\n" +
//            "               <BalAmt>\n" +
//            "                  <amountValue>0.00</amountValue>\n" +
//            "                  <currencyCode>KES</currencyCode>\n" +
//            "               </BalAmt>\n" +
//            "            </AcctBal>\n" +
//            "            <AcctBal>\n" +
//            "               <BalType>EFFAVL</BalType>\n" +
//            "               <BalAmt>\n" +
//            "                  <amountValue>8054854.30</amountValue>\n" +
//            "                  <currencyCode>KES</currencyCode>\n" +
//            "               </BalAmt>\n" +
//            "            </AcctBal>\n" +
//            "            <AcctBal>\n" +
//            "               <BalType>EFUAVL</BalType>\n" +
//            "               <BalAmt>\n" +
//            "                  <amountValue>0.00</amountValue>\n" +
//            "                  <currencyCode/>\n" +
//            "               </BalAmt>\n" +
//            "            </AcctBal>\n" +
//            "            <AcctBal>\n" +
//            "               <BalType>EMRADV</BalType>\n" +
//            "               <BalAmt>\n" +
//            "                  <amountValue>0.00</amountValue>\n" +
//            "                  <currencyCode>KES</currencyCode>\n" +
//            "               </BalAmt>\n" +
//            "            </AcctBal>\n" +
//            "            <AcctBal>\n" +
//            "               <BalType>CLNEMR</BalType>\n" +
//            "               <BalAmt>\n" +
//            "                  <amountValue>0.00</amountValue>\n" +
//            "                  <currencyCode>KES</currencyCode>\n" +
//            "               </BalAmt>\n" +
//            "            </AcctBal>\n" +
//            "            <AcctBal>\n" +
//            "               <BalType>CLNSTR</BalType>\n" +
//            "               <BalAmt>\n" +
//            "                  <amountValue>0.00</amountValue>\n" +
//            "                  <currencyCode>KES</currencyCode>\n" +
//            "               </BalAmt>\n" +
//            "            </AcctBal>\n" +
//            "            <AcctBal>\n" +
//            "               <BalType>SYSGENLM</BalType>\n" +
//            "               <BalAmt>\n" +
//            "                  <amountValue>0.00</amountValue>\n" +
//            "                  <currencyCode>KES</currencyCode>\n" +
//            "               </BalAmt>\n" +
//            "            </AcctBal>\n" +
//            "            <AcctBal>\n" +
//            "               <BalType>UNCLRBAL</BalType>\n" +
//            "               <BalAmt>\n" +
//            "                  <amountValue>0.00</amountValue>\n" +
//            "                  <currencyCode>KES</currencyCode>\n" +
//            "               </BalAmt>\n" +
//            "            </AcctBal>\n" +
//            "            <AcctBal>\n" +
//            "               <BalType>POOLAVL</BalType>\n" +
//            "               <BalAmt>\n" +
//            "                  <amountValue>0.00</amountValue>\n" +
//            "                  <currencyCode>KES</currencyCode>\n" +
//            "               </BalAmt>\n" +
//            "            </AcctBal>\n" +
//            "            <AcctBal>\n" +
//            "               <BalType>HOMAVL</BalType>\n" +
//            "               <BalAmt>\n" +
//            "                  <amountValue>0.00</amountValue>\n" +
//            "                  <currencyCode>KES</currencyCode>\n" +
//            "               </BalAmt>\n" +
//            "            </AcctBal>\n" +
//            "            <AcctBal>\n" +
//            "               <BalType>FUTCR</BalType>\n" +
//            "               <BalAmt>\n" +
//            "                  <amountValue>0.00</amountValue>\n" +
//            "                  <currencyCode>KES</currencyCode>\n" +
//            "               </BalAmt>\n" +
//            "            </AcctBal>\n" +
//            "            <AcctBal>\n" +
//            "               <BalType>UTILFUTBAL</BalType>\n" +
//            "               <BalAmt>\n" +
//            "                  <amountValue>3714.00</amountValue>\n" +
//            "                  <currencyCode>KES</currencyCode>\n" +
//            "               </BalAmt>\n" +
//            "            </AcctBal>\n" +
//            "            <AcctBal>\n" +
//            "               <BalType>USEDOC</BalType>\n" +
//            "               <BalAmt>\n" +
//            "                  <amountValue>0.00</amountValue>\n" +
//            "                  <currencyCode>KES</currencyCode>\n" +
//            "               </BalAmt>\n" +
//            "            </AcctBal>\n" +
//            "            <AcctBal>\n" +
//            "               <BalType>DAFAWITHDRAWL</BalType>\n" +
//            "               <BalAmt>\n" +
//            "                  <amountValue>0.00</amountValue>\n" +
//            "                  <currencyCode/>\n" +
//            "               </BalAmt>\n" +
//            "            </AcctBal>\n" +
//            "         </BalInqRs>\n" +
//            "         <BalInq_CustomData/>\n" +
//            "      </BalInqResponse>\n" +
//            "   </Body>\n" +
//            "</FIXML>";}
}


