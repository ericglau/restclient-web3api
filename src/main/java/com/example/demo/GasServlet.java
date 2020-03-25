package com.example.demo;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.api.ApiException;
import com.example.demo.api.Web3Api;
import com.example.demo.models.GasPrediction;
import com.example.demo.models.GasPredictions;

import org.eclipse.microprofile.rest.client.inject.RestClient;

@WebServlet(urlPatterns="/gas")
public class GasServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final long GWEI = 1000000000;

    @Inject
    @RestClient
    Web3Api api;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        GasPredictions gasPredictions;
        try {
            gasPredictions = api.gasPredictions("UAK000000000000000000000000demo0001", "ethereum-mainnet").getPayload();
        } catch (ApiException e) {
            response.getWriter().append("Web3API returned with exception: " + e.getMessage());
            return;
        }
        GasPrediction fast = gasPredictions.getFast();
        GasPrediction average = gasPredictions.getAverage();
        GasPrediction safeLow = gasPredictions.getSafeLow();

        response.getWriter().append("Fast: " + (fast.getGasPrice().longValue() / GWEI) + " Gwei, " + fast.getWait() + " minutes wait\n");
        response.getWriter().append("Average: " + (average.getGasPrice().longValue() / GWEI) + " Gwei, " + average.getWait() + " minutes wait\n");
        response.getWriter().append("Safe Low: " + (safeLow.getGasPrice().longValue() / GWEI) + " Gwei, " + safeLow.getWait() + " minutes wait\n");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        doGet(request, response);
    }
}