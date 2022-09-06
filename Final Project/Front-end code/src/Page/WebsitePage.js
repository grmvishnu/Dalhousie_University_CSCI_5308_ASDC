import React from "react";
import "../Css/WebsitePage.css";
import WebsitePageText from "./WebsitePageText";
import "../Css/WebsitePageText.css";
import WebsiteHomePageHeader from "../Component/WebsiteHomePageHeader";


export default function WebsitePage ()
{
    return (
        /* main page here and in css file means the navigation bar*/
        <div className="mainpage">
            <WebsiteHomePageHeader />
            <WebsitePageText />
        </div>
    )
}
