/**
 * Copyright 2011 Pablo Mendes, Max Jakob
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.dbpedia.spotlight.web.rest;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 * REST Web Service
 */

@Path("/disambiguate")
@Consumes("text/plain")
public class Disambiguate {
    @Context
    private UriInfo context;

    // Disambiguation interface
    private static SpotlightInterface disambigInterface = SpotlightInterface.getInstance(Server.getDisambiguator(), Server.getConfiguration());

    @GET
    @Produces("text/html")
    public String getHTML(@DefaultValue(ServerConfiguration.DEFAULT_TEXT) @QueryParam("text") String text,
                          @DefaultValue(ServerConfiguration.DEFAULT_CONFIDENCE) @QueryParam("confidence") Double confidence,
                          @DefaultValue(ServerConfiguration.DEFAULT_SUPPORT) @QueryParam("support") int support,
                          @DefaultValue(ServerConfiguration.DEFAULT_TYPES) @QueryParam("types") String dbpediaTypes,
                          @DefaultValue(ServerConfiguration.DEFAULT_SPARQL) @QueryParam("sparql") String sparqlQuery,
                          @DefaultValue(ServerConfiguration.DEFAULT_POLICY) @QueryParam("policy") String policy,
                          @DefaultValue(ServerConfiguration.DEFAULT_COREFERENCE_RESOLUTION) @QueryParam("coreferenceResolution") boolean coreferenceResolution) throws Exception {

        return disambigInterface.getHTML(text, confidence, support, dbpediaTypes, sparqlQuery, policy, coreferenceResolution);
    }

    @GET
    @Produces("application/xhtml+xml")
    public String getRDFa(@DefaultValue(ServerConfiguration.DEFAULT_TEXT) @QueryParam("text") String text,
                          @DefaultValue(ServerConfiguration.DEFAULT_CONFIDENCE) @QueryParam("confidence") Double confidence,
                          @DefaultValue(ServerConfiguration.DEFAULT_SUPPORT) @QueryParam("support") int support,
                          @DefaultValue(ServerConfiguration.DEFAULT_TYPES) @QueryParam("types") String dbpediaTypes,
                          @DefaultValue(ServerConfiguration.DEFAULT_SPARQL) @QueryParam("sparql") String sparqlQuery,
                          @DefaultValue(ServerConfiguration.DEFAULT_POLICY) @QueryParam("policy") String policy,
                          @DefaultValue(ServerConfiguration.DEFAULT_COREFERENCE_RESOLUTION) @QueryParam("coreferenceResolution") boolean coreferenceResolution) throws Exception {

        return disambigInterface.getRDFa(text, confidence, support, dbpediaTypes, sparqlQuery, policy, coreferenceResolution);
    }

    @GET
    @Produces("text/xml")
    public String getXML(@DefaultValue(ServerConfiguration.DEFAULT_TEXT) @QueryParam("text") String text,
                         @DefaultValue(ServerConfiguration.DEFAULT_CONFIDENCE) @QueryParam("confidence") Double confidence,
                         @DefaultValue(ServerConfiguration.DEFAULT_SUPPORT) @QueryParam("support") int support,
                         @DefaultValue(ServerConfiguration.DEFAULT_TYPES) @QueryParam("types") String dbpediaTypes,
                         @DefaultValue(ServerConfiguration.DEFAULT_SPARQL) @QueryParam("sparql") String sparqlQuery,
                         @DefaultValue(ServerConfiguration.DEFAULT_POLICY) @QueryParam("policy") String policy,
                         @DefaultValue(ServerConfiguration.DEFAULT_COREFERENCE_RESOLUTION) @QueryParam("coreferenceResolution") boolean coreferenceResolution) throws Exception {

        return disambigInterface.getXML(text, confidence, support, dbpediaTypes, sparqlQuery, policy, coreferenceResolution);
    }

    @GET
    @Produces("application/json")
    public String getJSON(@DefaultValue(ServerConfiguration.DEFAULT_TEXT) @QueryParam("text") String text,
                          @DefaultValue(ServerConfiguration.DEFAULT_CONFIDENCE) @QueryParam("confidence") Double confidence,
                          @DefaultValue(ServerConfiguration.DEFAULT_SUPPORT) @QueryParam("support") int support,
                          @DefaultValue(ServerConfiguration.DEFAULT_TYPES) @QueryParam("types") String dbpediaTypes,
                          @DefaultValue(ServerConfiguration.DEFAULT_SPARQL) @QueryParam("sparql") String sparqlQuery,
                          @DefaultValue(ServerConfiguration.DEFAULT_POLICY) @QueryParam("policy") String policy,
                          @DefaultValue(ServerConfiguration.DEFAULT_COREFERENCE_RESOLUTION) @QueryParam("coreferenceResolution") boolean coreferenceResolution) throws Exception {

        return disambigInterface.getJSON(text, confidence, support, dbpediaTypes, sparqlQuery, policy, coreferenceResolution);
    }

}