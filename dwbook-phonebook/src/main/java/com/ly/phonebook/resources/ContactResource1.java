package com.ly.phonebook.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * This is where REST action defined. Where what does does defined
 *
 * <p>
 * RESTful Convention
 * <p>
 * POST = create
 * GET = get
 * PUT = update
 * DELETE = delete
 * <p>
 * HTTP Response Code
 * 201 Created (for POST)
 * 204 No Content (for DELETE and response sent to client does nto include an entity)
 * 200 OK (for GET)
 */

@Path("/contact")                       //URI Template (Uniform Resorce Identifier)
@Produces(MediaType.APPLICATION_JSON)   //Response Content Type
public class ContactResource1 {

    //GET
    @GET
    @Path("/{id}")
    public Response getContact(@PathParam("id") int id) {

        return Response.ok("{contact.id: " + id + ",name : \"Dummy Name\", phone: \"+0123456789\"}").build();

        //Response.ok accept an object instance as parameter
        //Response.ok then serialize input to response format (the one you set in @Produces at the beginning of the class)
        //Response.ok will return HTTP 200 OK to client
    }

    //SET
    @POST
    public Response createContact(@FormParam("name") String name, @FormParam("phone") String phone) {
        return Response.created(null).build();
        //Response.created return HTTP 201 Created along with URI of new created resource.
        //The value passed in created input will be Location header of the response (it can be null)
    }

    //DELETE
    @Path("/{id}")
    @DELETE
    public Response deleteContact(@PathParam("id") int id) {
        return Response.noContent().build();
    }
    //Reponse.noContent() return HTTP 204 No Content
    //Response.noContent() indidateces no content applicable to this request

    //UPDATE
    @Path("/{id}")
    @PUT
    public Response updateContact(@PathParam("id") int id,                      //Notice this is PathParam from path   eg. contact/id/123
                                  @FormParam("firstName") String firstName,     //Notice this is FormParam from form   eg.
                                  //curl -X PUT -d 'firstName=FOO&lastName=BAR&phone=123' http://localhost:8080/contact/123

                                  @FormParam("lastName") String lastName,
                                  @FormParam("phone") String phone) { // here we can expect a complete object as well

        return Response.ok("{contact_id: " + id + ", name: \"" + firstName + "\", phone: \"" + phone + "\"} ").build();
        //See here? we don't pass in a string of JSON format anymore, we pass in an object. Jaskson converts it for us.

    }
    // All Code in example above are predefine code.
    // Response let us create custom response code like this Response.status(Status.MOVED_PERMANENTLY);
    //Response.created(location) = Response.status(someStatus).entity(null);
}
