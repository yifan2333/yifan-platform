package com.yifan.controller;

import org.springframework.stereotype.Controller;

/**
 * Created by wxy on 2017/4/4 0004.
 */
@Controller
//@SessionAttributes("authorizationRequest")
public class ConfirmAccessController {
//    @Resource
//    private JdbcClientDetailsService jdbcClientDetailsService;
//    @Resource
//    private ApprovalStore approvalStore;
//
//    @RequestMapping("/oauth/confirm_access")
//    public String getAccessConfirmation(Map<String, Object> model, Principal principal) {
//        AuthorizationRequest clientAuth = (AuthorizationRequest) model.remove("authorizationRequest");
//        ClientDetails client = jdbcClientDetailsService.loadClientByClientId(clientAuth.getClientId());
//        model.put("auth_request", clientAuth);
//        model.put("client", client);
//        Map<String, String> scopes = new LinkedHashMap<>();
//        for (String scope : clientAuth.getScope()) {
//            scopes.put(OAuth2Utils.SCOPE_PREFIX + scope, "false");
//        }
//        for (Approval approval : approvalStore.getApprovals(principal.getName(), client.getClientId())) {
//            if (clientAuth.getScope().contains(approval.getScope())) {
//                scopes.put(OAuth2Utils.SCOPE_PREFIX + approval.getScope(),
//                        approval.getStatus() == Approval.ApprovalStatus.APPROVED ? "true" : "false");
//            }
//        }
//        model.put("scopes", scopes);
//
//        return "access_confirmation";
//    }
}
