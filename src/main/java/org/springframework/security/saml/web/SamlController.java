package org.springframework.security.saml.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.opensaml.saml2.metadata.provider.MetadataProviderException;
import org.opensaml.ws.message.encoder.MessageEncodingException;
import org.opensaml.xml.util.XMLHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.saml.SAMLCredential;
import org.springframework.security.saml.util.SAMLUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceView;

/**
 * @author Administrator
 * @date 2018/9/8 21:35
 */
@Controller
@RequestMapping("/sam")
public class SamlController {

    private static Log log = LogFactory.getLog(SamlController.class);

    @RequestMapping("/login")
    public ModelAndView metadataList() throws MetadataProviderException, MessageEncodingException {

        ModelAndView model = new ModelAndView(new InternalResourceView("/WEB-INF/login/index2.jsp", true));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
        log.info("authentication:"+authentication);
        log.info("credential:"+credential);
        log.info("assertion:"+XMLHelper.nodeToString(SAMLUtil.marshallMessage(credential.getAuthenticationAssertion())));


        return model;

    }
}
