import { Box, Center, Spinner } from "@chakra-ui/react";

interface LoadingPageProps{
    show: boolean;
}

function LoadingPage ( {show}: LoadingPageProps ) {

    if(show){
        return (
                <div className="spinnerContainer">
                    <Spinner size='xl' className="spinner"/>
                </div>
        )
    }

    return null;

}

export default LoadingPage;