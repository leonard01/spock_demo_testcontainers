class HttpRequest {


    HttpURLConnection con

    def HTTPRequest(URL url) {
        HttpURLConnection con = (HttpURLConnection) url.openConnection()
        con.setRequestMethod("GET")
        con.getInputStream()
        this.con = con
    }

    def HTTPRequest(URL url, String reqType) {
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod(reqType)
        con.getInputStream()
        this.con = con
    }

    def searchResponse(expectedResponse) {

        def br = new BufferedReader(new InputStreamReader((this.con.getInputStream())));
        def sb = new StringBuilder();
        String output
        int i = 0
        while ((!sb.contains(expectedResponse)) && i < 60) {
            output = br.readLine()
            sb.append(output)
            //Uncomment following three lines for debugging if tests failing
//            println(sb.toString())
//            println(output)
//            println("i = " + i)
//            i++;
            if (i == 60) {
                return false
            }
        }
        return true
    }
}



